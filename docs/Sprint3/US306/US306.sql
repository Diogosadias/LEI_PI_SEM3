--US306--
create or replace PROCEDURE checkWarehouseOccupancyRate
IS
   container_count NUMBER;
   warehouse_capacity NUMBER;
   occupancy FLOAT;

BEGIN
    --Loop que vai correr todos os warehouses--
    FOR warehouse_rec IN(SELECT warehouse_id FROM Warehouse) 
    LOOP
        
        --Vai buscar a contagem de containers em cada warehouse--
        SELECT COUNT(container_id)
        INTO container_count
        FROM Container
        WHERE warehouse_id = warehouse_rec.warehouse_id;
        
        --Vai buscar a capacity de cada warehouse--
        SELECT capacity
        INTO warehouse_capacity 
        FROM Warehouse 
        WHERE warehouse_id = warehouse_rec.warehouse_id;
        
        occupancy := (container_count/warehouse_capacity)*100;
        dbms_output.put_line('The occupancy rate of the warehouse ' || warehouse_rec.warehouse_id || ' is: ' || occupancy || '%');
        
        --Para cada warehouse vai buscar os containers que vão sair nos próximos 30 dias--
        FOR CUR_VAR IN(
        SELECT container_id 
        FROM Container 
        WHERE warehouse_id = warehouse_rec.warehouse_id 
            AND container_id = (SELECT container_id
                FROM Container_Trip WHERE manifest_load = (SELECT manifest_load_id
                    FROM Manifest_Load WHERE to_char(to_date(base_date_time,'yyyymmdd'),'MM-DD-YYYY HH24:MI:SS') <= TRUNC(SYSDATE) + 30)))
                    LOOP
                    dbms_output.put_line('The container with the ID ' || CUR_VAR.container_id || ' will be leaving the warehouse ' || warehouse_rec.warehouse_id || ' in the next 30 days!');
                    END LOOP;
        
    END LOOP;

END;
/
