--Ship, ship1
SELECT getCurrentSituationOfContainer(1,15) FROM DUAL;
--ERROR CODE 11 - Container is not leased by client.
SELECT getCurrentSituationOfContainer(2,15) FROM DUAL;
--invalid container_id -> ERROR CODE 11
SELECT getCurrentSituationOfContainer(1,99) FROM DUAL;

create or replace function getCurrentSituationOfContainer(p_client_id integer, p_container_id integer) return VARCHAR
is
    --exceptions
    invalid_container_id exception;
    container_not_conected exception;
    
    --varibles
    v_container_count integer;
    v_client_container integer;
    v_location varchar(100);
    v_ship_name varchar(100);
    
    --return
    v_result VARCHAR(255);


    cursor cr is
    select location from Port 
    where port_id = (select port_id
                            from Container
                            where container_id = (select container_id 
                                from Container_Trip where manifest_unload is not null and container_id = p_container_id));

    cursor cr1 is
    select name from Ship
    where mmsi = (select ship_mmsi from Trip_Ship 
                        where trip_id = (select trip_id from Trip 
                                            where trip_id = (select trip_id from Container_Trip
                                                                    where container_id = p_container_id and manifest_unload is null and manifest_load is not null)));

begin
    --check if the container exist
    select count(*) into v_container_count
    from Container
	where container_id = p_container_id;
    if v_container_count = 0 then    
        raise invalid_container_id;
    end if;
    
    --check if the client is associated with the especific container
    select count (*) into v_client_container
    from Client
    where client_id = (select client_id from Container where client_id = p_client_id and container_id = p_container_id); 

    if v_client_container = 0 then 
        raise container_not_conected;
    end if;
    

    open cr;
    fetch cr into v_location;
    close cr;

    if v_location is not null then
        v_result := 'PORT,' || v_location; 
        return v_result;
    end if;

    open cr1;
    fetch cr1 into v_ship_name;
    close cr1;

    if v_ship_name is not null then
        v_result := 'SHIP,' || v_ship_name; 
        return v_result;
    end if;

    
exception
    when invalid_container_id then
        raise_application_error(-20001,'ERROR CODE 10 - Invalid container id.');
    when container_not_conected then
        raise_application_error(-20001,'ERROR CODE 11 - Container is not leased by client.');
end;
