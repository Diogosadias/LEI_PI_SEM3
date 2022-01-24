CREATE OR REPLACE FUNCTION fncCheckAverageOccupancy(p_ship_id IN NUMBER,p_initial_date IN DATE,p_final_date IN DATE) RETURN VARCHAR2
IS

    container_count NUMBER;
    ship_capacity NUMBER;
    manifestAverage FLOAT;
    funcResult VARCHAR2(30000);
    no_container_exception EXCEPTION;

    --Cursor que guarda o conjunto de manifests associados ao navio pedido--
   CURSOR c_manifest IS
   SELECT manifest_id FROM Container
    WHERE container_id IN (SELECT container_id FROM Container_Trip
        WHERE trip_id IN (SELECT trip_id FROM Trip 
            WHERE mmsi = p_ship_id));

BEGIN

    funcResult := '';

    --Guarda a capacity do navio pedido-- 
    SELECT capacity  
    INTO ship_capacity
    FROM Ship 
    WHERE mmsi = p_ship_id;
    
    --Para cada resultado do cursor--
    FOR r_manifest IN c_manifest
    LOOP
        --Vai buscar a contagem de containers de cada manifest associado ao navio pedido que tenham sido criados dentro do período pedido--
        SELECT COUNT(container_id) INTO container_count FROM Container c WHERE c.manifest_id = r_manifest.manifest_id
            AND r_manifest.manifest_id IN (SELECT manifest_id FROM Cargo_Manifest WHERE TO_DATE(base_date_time,'DD/MM/YYYY HH24:MM')
                BETWEEN p_initial_date AND p_final_date);
                
        IF(container_count=0) THEN
            RAISE no_container_exception;
        END IF;

        manifestAverage := (container_count/ship_capacity)*100;
        funcResult := funcResult + 'The occupancy rate of the manifest ' || r_manifest.manifest_id || ' on the ship '|| p_ship_id ||'on the given time period
            is: ' || manifestAverage || '%';

    END LOOP;
    
    RETURN funcResult;


EXCEPTION
WHEN no_container_exception THEN
   raise_application_error(-20001,'There were no containers on the ship ' ||p_ship_id|| ' during the given period');
END;
/