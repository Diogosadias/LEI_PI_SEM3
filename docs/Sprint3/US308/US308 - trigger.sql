create or replace trigger VALIDA_SHIP_CAPACITY
before insert or update on trip_ship
for each row

declare ERRO EXCEPTION;
max_capacity number;
contador_dos_contentores_naquela_trip number;

BEGIN

select capacity
into max_capacity
from ship
where mmsi = :new.ship_mmsi; --destination ship

select count(container_id)
into contador_dos_contentores_naquela_trip
from container_trip
where trip_id =:new.trip_id;

--ship's avaiable capacity � dada pela m�xima capacidade daquele navio menos os contentores que est�o naquele navio e naquela viagem
if contador_dos_contentores_naquela_trip > max_capacity then
raise ERRO;
end if;

EXCEPTION
when ERRO then
raise_application_error(-20006,'A capacidade m�xima da ship foi ultrapassada');


END VALIDA_SHIP_CAPACITY;