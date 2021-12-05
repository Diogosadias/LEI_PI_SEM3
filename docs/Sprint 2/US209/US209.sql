--US209----

create or replace FUNCTION checkOccupancyRateMoment(p_ship_id IN INTEGER,p_moment VARCHAR2)
RETURN FLOAT
IS
   l_manifest Manifest_Unload.manifest_unload_id%TYPE;
   rate NUMBER;
   rateResult FLOAT;


   CURSOR c1 is
   SELECT manifest_unload_id FROM Manifest_Unload WHERE manifest_unload_id IN (SELECT manifest_unload FROM Container_Trip WHERE container_id IN (SELECT container_id FROM Trip WHERE trip_id
   IN (SELECT trip_id FROM Trip_Ship WHERE ship_mmsi IN (SELECT mmsi FROM Ship WHERE mmsi IN (SELECT ship_mmsi FROM Ship_Status WHERE base_date_time = p_moment)))));

BEGIN
    rateResult := 0;
    OPEN c1;
   LOOP
   fetch c1 into l_manifest;
    exit when c1%NOTFOUND;
    rate:=checkShipsOccupancyRate(p_ship_id,l_manifest);
    rateResult := rateResult + rate;

    END LOOP;

    CLOSE c1;

RETURN rateResult;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/

SELECT checkOccupancyRateMoment(999999999,'31/01/2019 17:19') FROM DUAL;