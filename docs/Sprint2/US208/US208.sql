--US208--
create or replace FUNCTION checkShipsOccupancyRate(p_ship_id IN NUMBER,p_manifest_id IN NUMBER)
RETURN FLOAT
IS
   container_count NUMBER;
   ship_capacity NUMBER;
   func_result FLOAT;
    
    --Guarda a quantidade de containers associados a um dado manifest--
   CURSOR c1 IS
   SELECT COUNT(container_id)
     FROM Container_Trip
     WHERE manifest_unload = p_manifest_id;
    
    --Guarda a capacity de um navio--
   CURSOR c2 IS
   SELECT capacity 
    FROM Ship 
    WHERE mmsi = p_ship_id;

BEGIN

   OPEN c1;
   FETCH c1 INTO container_count;
    
    --Se o cursor não devolveu nenhuma linha a contagem é 0--
   IF c1%notfound THEN
      container_count := 0;
   END IF;
   CLOSE c1;

   OPEN c2;
   FETCH c2 INTO ship_capacity;
   CLOSE c2;

   func_result := (container_count/ship_capacity)*100;

RETURN func_result;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/

--Manifest 55 tem 2 Containers no Ship 999999999--
SELECT checkShipsOccupancyRate(999999999, 55) FROM DUAL;
--Manifest 55 tem 1 Container no Ship 999999999--
SELECT checkShipsOccupancyRate(999999999, 56) FROM DUAL;
--Manifest 57 tem 3 Containers no Ship 999999999--
SELECT checkShipsOccupancyRate(999999999, 57) FROM DUAL;
--Manifest 58 tem 3 Containers no Ship 999999999--
SELECT checkShipsOccupancyRate(999999999, 58) FROM DUAL;
--Navio 999999999 100% preenchido dado que a sua capacity é de 10 containers--
