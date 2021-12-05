create or replace FUNCTION CHECKCONTAINERSTOBEOFFLOADED(p_trip_id IN INTEGER)
return SYS_REFCURSOR
AS
 my_cursor SYS_REFCURSOR;


BEGIN

  OPEN my_cursor FOR
    SELECT container.container_id, payload, x_coord,y_coord,z_coord, refrigerated_container.temperature as Type_ºC
    from Container_Trip INNER JOIN container ON container_trip.container_id = container.container_id INNER JOIN refrigerated_container ON refrigerated_container.container_id = container.container_id
    where manifest_unload IN (select manifest_unload_id from manifest_unload)and manifest_unload is not null and trip_id = p_trip_id;

  RETURN my_cursor;

EXCEPTION
WHEN OTHERS THEN
   return null;
END;