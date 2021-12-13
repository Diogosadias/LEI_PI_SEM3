create or replace function currentSituationOfAContainer(p_container_id in integer) return VARCHAR
is
    v_container_count integer;
    v_result VARCHAR(255);
    v_location varchar(100);
    v_ship_name varchar(100);

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
	select count(*) into v_container_count
		from Container
	where container_id = p_container_id;
	if v_container_count = 0 then
		return null;
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
    
    return null;    

   exception
   when others then
   return null;
end;
    
 
--test 
SELECT currentSituationOfAContainer(11) FROM DUAL;   
SELECT currentSituationOfAContainer(15) FROM DUAL;
SELECT currentSituationOfAContainer(89) FROM DUAL;


/* rascunho 
   select location from Port 
    where port_id = (select port_id
                            from Container
                            where container_id = (select container_id 
                                from Container_Trip where manifest_unload is not null and container_id = 11));
                                

    select name from Ship
    where mmsi = (select ship_mmsi from Trip_Ship 
                        where trip_id = (select trip_id from Trip 
                                            where trip_id = (select trip_id from Container_Trip
                                                                    where container_id = 15 and manifest_unload is null and manifest_load is not null)));
*/