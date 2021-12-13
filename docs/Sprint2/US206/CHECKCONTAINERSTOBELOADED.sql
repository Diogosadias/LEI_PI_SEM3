create or replace FUNCTION CHECKCONTAINERSTOBELOADED(p_trip_id IN INTEGER)
return SYS_REFCURSOR
AS
 my_cursor SYS_REFCURSOR;


BEGIN

  OPEN my_cursor FOR
  SELECT container.container_id, payload as Load, refrigerated_container.temperature as Type_ºC
    from Container_Trip INNER JOIN container ON container_trip.container_id = container.container_id INNER JOIN refrigerated_container ON refrigerated_container.container_id = container.container_id
    where manifest_load IN (select manifest_load_id from manifest_load)and manifest_load is not null and  trip_id = p_trip_id;


  RETURN my_cursor;

EXCEPTION
WHEN OTHERS THEN
   return null;
END;