
--Create temporay table
--DROP table SHIPS_IDLE_DAYS Cascade constraints Purge;
/*
create table SHIPS_IDLE_DAYS(
mmsi integer,
idle_days integer
);
*/

--delete from ships_idle_days;
--drop function fnc_get_idle_ships;
--delete from ships_idle_days where mmsi=111111111;

--Select fnc_get_idle_ships FROM DUAL;

create or replace FUNCTION fnc_get_idle_ships return sys_refcursor
as
    v_ship_mmsi  integer;
    v_days_counter   integer;
    v_final_mmsi  integer;
    all_ships sys_refcursor;
    idle_ship_result     sys_refcursor;


BEGIN
    v_days_counter := 0;
    OPEN all_ships for select mmsi FROM Ship; --guarda todos os mmsi 

    LOOP
        FETCH all_ships into v_ship_mmsi;
        --select mmsi into v_ship_mmsi from Ship;

        select mmsi INTO v_final_mmsi from Ship where mmsi = v_ship_mmsi;

        select fnc_get_number_of_days(v_final_mmsi) into v_days_counter from DUAL;
        proc_table_for_idle_ships(v_final_mmsi, v_days_counter);

        Exit when all_ships%notfound;
    end loop;

    OPEN idle_ship_result for SELECT * FROM SHIPS_IDLE_DAYS;
      
    
    return idle_ship_result;
end;


-----------------------------------------------------------------------------------------------------------


--drop function fnc_get_number_of_days;
--select * from Trip;
--Select fnc_get_number_of_days(111111111) FROM DUAL;
create or replace Function fnc_get_number_of_days(p_mmsi INTEGER) RETURN INTEGER
    IS
    
    trip_cursor   sys_refcursor;

    v_trip_id         integer;
    v_trip_counter integer;
    v_trip_days integer;
    v_trip_days_result integer;

    --variables for dates
    actual_date      date;
    actual_year integer;
    first_date date;

    --return variable 
    v_day_counter     integer;
    
    testar integer;
BEGIN
    actual_date := trunc(sysdate); -- data atual
    
    --datas do incio do ano 
    first_date := TO_DATE('01/01'||to_char(sysdate,'YYYY'),'dd/mm/YYYY'); --primeiro dia do ano
    v_day_counter :=0;
    
    --verificar se tem trip no ano indicado e ate à data atual 
    select count(*) into v_trip_counter 
    from Trip 
    where mmsi = (select mmsi 
                from Ship where mmsi = p_mmsi and (to_date(trip.base_date_time_origin, 'dd/mm/yyyy hh24:mi:ss') between first_date and actual_date) and to_date(trip.base_date_time_end, 'dd/mm/yyyy hh24:mi:ss') < actual_date);


    if v_trip_counter = 0
    then
        v_day_counter := extract(day from actual_date) - extract (day from first_date) + 1;
        return v_day_counter;
    end if;

    v_trip_days := 0;
    v_trip_days_result := 0;
    
    --contagem dos dias em que o navio esteve parado
    --cursor que guarda 
    OPEN trip_cursor for select trip_id from Trip 
        where mmsi = (select mmsi 
                    from Ship where mmsi = p_mmsi and (to_date(trip.base_date_time_origin, 'dd/mm/yyyy hh24:mi:ss') between first_date and actual_date) and to_date(trip.base_date_time_end, 'dd/mm/yyyy hh24:mi:ss') < actual_date);
    
    LOOP
        FETCH trip_cursor into v_trip_id;
        --conta os dias que uma viagem demora 
        select fnc_total_trip_days(v_trip_id) into v_trip_days from DUAL;

        v_trip_days_result := v_trip_days_result + v_trip_days; 
        Exit when trip_cursor%notfound;

    end loop;

    v_day_counter := (extract (day from actual_date) - extract (day from first_date)) + 1 - v_trip_days_result;
       
    if v_day_counter <= 0
    then 
    v_day_counter := 0;
    return v_day_counter;
    
    end if;

    return v_day_counter;
end;



---------------------------------------------------------------------------------------------------------------
 --Select fnc_total_trip_days(50) FROM DUAL;
 --Select fnc_total_trip_days(51) FROM DUAL;
 
 
 --drop function fnc_total_trip_days;
 -- count the number of days that a trip takes to do 
create or replace Function fnc_total_trip_days(p_trip_id INTEGER) RETURN INTEGER
is
v_total_days integer;
v_date_origin varchar(100);
v_date_destination varchar(100);

begin

select base_date_time_origin into v_date_origin from Trip where trip_id = p_trip_id;

select base_date_time_end into v_date_destination from Trip where trip_id = p_trip_id;

v_total_days := extract (day from to_date(v_date_destination, 'dd/mm/yyyy hh24:mi:ss')) - extract (day from to_date(v_date_origin, 'dd/mm/yyyy hh24:mi:ss')); 

return v_total_days;
end;


-----------------------------------------------------------------------------------------------------------
--store the mmssi and the days each ship has been idle since the begining of the current year
--drop PROCEDURE proc_table_for_idle_ships;
create or replace PROCEDURE proc_table_for_idle_ships(p_mmsi INTEGER, p_days_counter integer)
AS
    pragma autonomous_transaction; -- evita dar exception : changes the way a subprogram works within a transaction
    counter integer;
    
Begin
        counter := 0;
        select count(*) into counter from ships_idle_days where mmsi = p_mmsi;
        if counter<1
        then
        Insert into SHIPS_IDLE_DAYS (mmsi, idle_days) VALUES (p_mmsi, p_days_counter);
    end if;
    commit;

END;
/


--testes auziliares 
/*
    select count(*)  
    from Trip 
    where mmsi = (select mmsi 
                from Ship where mmsi = 888888888 and (to_date(trip.base_date_time_origin, 'dd/mm/yyyy hh24:mi:ss') between TO_DATE('01/01'||to_char(sysdate,'YYYY'),'dd/mm/YYYY') and sysdate) and to_date(trip.base_date_time_end, 'dd/mm/yyyy hh24:mi:ss') < sysdate);
                
        
        
 select extract(day from sysdate) - extract (day from TO_DATE('01/01'||to_char(sysdate,'YYYY'),'dd/mm/YYYY')) + 1 from dual;        
 */       



