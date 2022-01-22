drop function fnc_idle_ships;

create or replace FUNCTION fnc_get_idle_ships return sys_refcursor
as
    v_ship_mmsi  integer;
    v_days_counter   integer;
    v_final_mmsi  integer;
    all_ships sys_refcursor;
    idle_ship_result     sys_refcursor;


BEGIN
    OPEN all_ships for select mmsi FROM Ship; --guarda todos os mmsi 
    
    LOOP
        FETCH all_ships into v_ship_mmsi;
        --select mmsi into v_ship_mmsi from Ship;
        
        select mmsi INTO v_final_mmsi from Ship where mmsi = v_ship_mmsi;

        select fnc_get_number_of_days(v_final_mmsi) into v_days_counter from DUAL;
        proc_table_for_idle_ships(v_ship_mmsi, v_days_counter);

        Exit when all_ships%notfound;
    end loop;

    OPEN idle_ship_result for SELECT * FROM SHIPS_IDLE_DAYS;

    return idle_ship_result;
end;


--Select fnc_idle_ships FROM DUAL;



--drop function fnc_get_number_of_days;

--não sei se ajuda mas o ano atual consegues assim year integer := to_char(sysdate,'YYYY');
--e o dia inicail do ano atual assim day_one_year date:= TO_DATE('01/01'||to_char(sysdate,'YYYY'),'dd/mm/YYYY');

create Function fnc_get_number_of_days(p_mmsi INTEGER) RETURN INTEGER
    IS
    
    trip_cursor   sys_refcursor;
    
    v_trip_id         integer;
    v_trip_counter integer;
    v_trip_days integer;
    v_trip_days_result integer;
    
    --variables for dates
    actual_date      timeStamp(6);
    first_day date;
    v_date date;
    actual_year integer;
    --actual_date date;
    
    --return variable 
    v_day_counter     integer;

BEGIN
    actual_date := TO_TIMESTAMP(LOCALTIMESTAMP, 'DD/MON/RR HH:MI:SSXFF'); -- hora atual
    --first_date:= TO_DATE('01/01'||to_char(sysdate,'YYYY'),'dd/mm/YYYY'); --primeiro dia do ano
    --actual_year := to_char(sysdate,'YYYY'); --ano atual
    v_date := TO_DATE(to_char(sysdate,'YYYY/MM/DD'),'dd/mm/YYYY');  -- data do inivio do ano dd/mm/yyyy
    

    --verificar se tem trip no ano indicado e ate à data atual 
    select count(*) into v_trip_counter 
    from Trip 
    where ship_mmsi = (select mmsi 
                                from Ship where mmsi = p_mmsi and trip.base_date_time_origin > v_date and trip.base_date_time_origin < actual_date and trip.base_date_time_destination < actual_date);
    
    
    
    if v_trip_counter = 0
    then
        v_day_conter := actual_date - v_date;
        return v_day_counter;
        
        
    else 
        --contagem dos dias em que o navio esteve parado
        --cursor que guarda 
        OPEN trip_cursor for select trip_id from Trip 
        where ship_mmsi = (select mmsi 
                    from Ship where mmsi = p_mmsi and trip.base_date_time_origin > v_date and trip.base_date_time_origin < actual_date and trip.base_date_time_destination < actual_date);
    
    
    LOOP
        FETCH position_cursor into v_trip_id;

        select fnc_total_trip_days(v_trip_id) into v_trip_days from DUAL;

        v_trip_days_result := v_trip_days_result + v_trip_days; 

        Exit when position_cursor%notfound;
    end loop;

    /*
    if ((EXTRACT(DAY FROM firstPositionDate) - '01/01/2022 00:01' > 1) and
        (EXTRACT(YEAR FROM firstPositionDate) = 2022)) then
        dayCount := dayCount + (EXTRACT(DAY FROM firstPositionDate - '01/01/2022 00:01'));

    else
        return 0;
    end if;

    end if;
   */
    end if;
    
    v_day_counter := (actual_date - v_date) - v_trip_days_result;
   
    if v_day_counter <= 0
    then 
    v_day_counter := 0;
    return v_day_counter;
    end if;
   
    
    return v_day_counter;
end;

 -- count the number of days that a trip takes to do 
create Function fnc_total_trip_days(p_trip_id INTEGER) RETURN INTEGER
is
v_total_days integer;
v_date_origin date;
v_date_destination date;

begin

select base_date_time_origin into v_date_origin from Trip where trip_id = p_trip_id;

select base_date_time_destination into v_date_destination from Trip where trip_id = p_trip_id;

v_total_days := v_date_destination - v_date_origin; 


return v_total_days;
end;




--drop PROCEDURE proc_table_for_idle_ships;
--ATENÇAO CRIAR TABELA TEMPORARIA 
create PROCEDURE proc_table_for_idle_ships(p_mmsi INTEGER, p_days_counter integer)
AS
    pragma autonomous_transaction; -- evita dar exception : changes the way a subprogram works within a transaction
    mmsi_counter integer;
Begin
    select count(*) into mmsi_counter from SHIPS_IDLE_DAYS iships where iships.mmsi = p_mmsi;
    
    --verificar se na tabela temporaria ja existe o mmsi que pretende se colocar
    if mmsi_counter = 0
    then
        Insert into SHIPS_IDLE_DAYS VALUES (p_mmsi, p_days_counter);
    end if;
    
    commit;
END;
/







--    select count(*) from Trip 
--    where ship_mmsi = (select mmsi 
--                                from Ship where mmsi = p_mmsi and trip.base_date_time_origin > date '01/01/2022 00:01' and trip.base_date_time_origin < date '22/01/2022 23:59' and trip.base_date_time_destination < date '22/01/2022 23:59');



