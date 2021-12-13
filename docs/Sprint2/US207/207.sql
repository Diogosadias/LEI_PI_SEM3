create or replace FUNCTION US_207(p_captain_id in integer )
RETURN VARCHAR AS 


 v_Manifests integer;
 V_avg FLOAT;
 v_containerc integer;
 
CURSOR Manifests
is
Select COUNT(trip_id)
from trip 
where base_date_time_origin like '%2019%'
and trip_id 
IN 
(select trip_id from trip_ship 
where ship_mmsi
=(select ship_mmsi from ship where captain_id = p_captain_id))
;

CURSOR Containerz
is
Select count(container_id)
from Container_Trip
where trip_id IN (select trip_id from trip where trip_id IN (select trip_id from trip_ship where ship_mmsi = (select ship_mmsi from ship where captain_id = p_captain_id)));

 
BEGIN

open Manifests;
FETCH Manifests into v_manifests;

CLOSE Manifests;
  
open   Containerz ;
  fetch Containerz into  v_containerc;
  
  CLOSE Containerz;
  V_avg := v_containerc / v_manifests;
  
  return  v_manifests|| ',' || V_avg ;
  exception
   when others then
   return null;
END US_207;