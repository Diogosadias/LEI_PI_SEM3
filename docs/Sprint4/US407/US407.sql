create or replace FUNCTION GenerateLoadingAndUnloadingMap (p_port_id IN INTEGER)
return SYS_REFCURSOR
AS
 my_cursor SYS_REFCURSOR;

 ERRO EXCEPTION;

--varibles

only_needed_trips trip.trip_id%TYPE;
count_containers INTEGER;
transporter_id INTEGER;
    

CURSOR c1 is
select trip_id
from trip
where ((destination = (select location from Port where port_id = p_port_id)and (base_date_time_end between trunc(sysdate) and trunc(sysdate+7))) --week in advance is the period between those dates
       or(origin = (select location from Port where port_id = p_port_id)and (base_date_time_origin between trunc(sysdate) and trunc(sysdate+7)))); --searching only trips that start/end on the port that the manager controls and in the needed period of time

BEGIN

 OPEN c1;
   LOOP
   fetch c1 into only_needed_trips;
       exit when c1%NOTFOUND;
           END LOOP;

CLOSE c1;

OPEN my_cursor FOR
select Cargo_Manifest.manifest_id, type , container.container_id, Cargo_Manifest.trip_id, base_date_time, transporter_id
from Cargo_Manifest INNER JOIN container ON cargo_manifest.manifest_id = container.manifest_id INNER JOIN Trip ON cargo_Manifest.trip_id = trip.trip_id 
where Cargo_Manifest.trip_id in only_needed_trips and ((transporter_id = trip.mmsi and trip.mmsi is not null) or (transporter_id = trip.truck_id and trip.truck_id is not null));


  RETURN my_cursor;


EXCEPTION
when ERRO then
 raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
        
END GenerateLoadingAndUnloadingMap;