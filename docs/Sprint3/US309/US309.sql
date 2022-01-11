--US309--
create or replace TRIGGER isShipMoving
   BEFORE INSERT ON Container_Trip
   FOR EACH ROW
   DECLARE
     shipMMSI Ship.mmsi%type;
     manifestID Manifest_Load.manifest_load_id%type;
     manifestDate Manifest_Load.base_date_time%type;
     tripStart Trip.base_date_time_origin%type;
     valid INTEGER;
     moving EXCEPTION;
BEGIN
    
    --Guarda no manifestID o manifest ID que vai ser inserido--
    SELECT manifest_load_id INTO manifestID FROM Manifest_Load WHERE manifest_load_id = :new.manifest_load;
    
    --Vai buscar o Ship MMSI que faz a viagem associada ao novo manifest--
    SELECT ship_mmsi INTO shipMMSI FROM Trip_Ship ts INNER JOIN Trip t ON ts.trip_id = t.trip_id
        WHERE t.trip_id = (SELECT trip_id FROM Container_Trip WHERE manifest_load = manifestID);
        
    --Vai buscar a data de início da viagem associada ao novo manifest--    
    SELECT base_date_time_origin INTO tripStart FROM Trip t INNER JOIN Container_Trip ct ON t.trip_id = ct.trip_id
        WHERE ct.manifest_load = manifestID;
    
    IF (tripStart IS NOT NULL) THEN
        --Se o INSERT for feito antes do início da viagem, o INSERT é válido--
        IF (TO_CHAR(TO_DATE(SYSDATE, 'DD-MM-YYYY HH24:MI'), 'DD/MM/YYYY hh24:mi') < TO_CHAR(TO_DATE(tripStart,'DD/MM/YYYY HH24:MI'),'DD/MM/YYYY hh24:mi')) THEN
            valid:=1;
        ELSE
            valid:=0;
        END IF;
    ELSE
        valid:=0;
    END IF;
    
    --Se inválido, levantar exceção antes do insert--
    IF(valid=0) THEN
        RAISE moving;
    END IF;

   EXCEPTION
     WHEN moving THEN
       raise_application_error(-20001,'The ship ' ||shipMMSI|| ' is moving and can not load any containers');
END isShipMoving;
/