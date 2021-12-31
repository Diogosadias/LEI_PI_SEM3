create or replace trigger VALIDA_WAREHOUSE_CAPACITY
before insert or update on container_trip
for each row

declare ERRO EXCEPTION;
contador_dos_contentores_do_novo_manifest number;
contador_dos_contentores_na_warehouse number;
warehouse_destino number;
max_capacity number;

BEGIN

select count(container_id)
into contador_dos_contentores_do_novo_manifest
from container_trip
where manifest_unload = :new.manifest_unload;

select warehouse_id
into warehouse_destino
from warehouse
where warehouse_id = (select warehouse_id from container where container_id = :new.container_id);

select capacity into max_capacity from warehouse where warehouse_id = warehouse_destino;

select count(container_id)
into contador_dos_contentores_na_warehouse
from container
where warehouse_id = warehouse_destino;

if (contador_dos_contentores_do_novo_manifest + contador_dos_contentores_na_warehouse) > max_capacity then
raise ERRO;
end if;

EXCEPTION
when ERRO then
raise_application_error(-20006,'A capacidade máxima da warehouse foi ultrapassada');


END VALIDA_WAREHOUSE_CAPACITY;