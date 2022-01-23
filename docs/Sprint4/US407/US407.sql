
create or replace FUNCTION GenerateLoadingAndUnloadingMap (p_port_id IN INTEGER)
return SYS_REFCURSOR
AS
 my_cursor SYS_REFCURSOR;

 ERRO EXCEPTION;

--variables

only_needed_trips number;
count_containers INTEGER;
transporter_id INTEGER;
    

BEGIN


OPEN my_cursor FOR

select Cargo_Manifest.manifest_id, Cargo_Manifest.type , container.container_id, container_trip.x_coord, container_trip.y_coord ,container_trip.z_coord , Cargo_Manifest.trip_id, Cargo_Manifest.base_date_time
from Cargo_Manifest INNER JOIN container ON cargo_manifest.manifest_id = container.manifest_id INNER JOIN container_trip ON container.container_id = container_trip.container_id,
(select trip_id from trip 
where ((destination = (select name from Port where port_id = p_port_id) and(to_date(base_date_time_end,'dd/mm/yyyy hh24:mi') between trunc(sysdate) and trunc(sysdate+7)))
or (origin = (select name from Port where port_id = p_port_id)and (to_date(base_date_time_origin,'dd/mm/yyyy hh24:mi') between trunc(sysdate) and trunc(sysdate+7)))))C
 where Cargo_Manifest.trip_id in C.trip_id;


  RETURN my_cursor;


EXCEPTION
when ERRO then
 raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
        
END GenerateLoadingAndUnloadingMap;