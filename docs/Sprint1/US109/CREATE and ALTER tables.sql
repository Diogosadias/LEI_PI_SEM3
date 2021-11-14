
Drop table ISO_Code Cascade constraints Purge;
Drop table  Refrigerated_Container Cascade constraints Purge;
Drop table Manifest Cascade constraints Purge;
DROP TABLE Manifest_Warehouse      CASCADE CONSTRAINTS PURGE;
DROP TABLE Warehouse                CASCADE CONSTRAINTS PURGE;
DROP TABLE Warehouse_Manager         CASCADE CONSTRAINTS PURGE;
DROP TABLE Connection_Warehouse         CASCADE CONSTRAINTS PURGE;
DROP TABLE Warehouse_Staff             CASCADE CONSTRAINTS PURGE;
DROP TABLE Port_Manifest    CASCADE CONSTRAINTS PURGE;
DROP TABLE Truck_Manifest      CASCADE CONSTRAINTS PURGE;
Drop TABLE Truck        CASCADE CONSTRAINTS PURGE;
DROP TABLE Driver        CASCADE CONSTRAINTS PURGE;
DROP TABLE Ship_Manifest         CASCADE CONSTRAINTS PURGE;
DROP TABLE Ship    CASCADE CONSTRAINTS PURGE;
DROP TABLE Ship_Status    CASCADE CONSTRAINTS PURGE;
DROP TABLE Fleet    CASCADE CONSTRAINTS PURGE;
DROP TABLE Fleet_Manager    CASCADE CONSTRAINTS PURGE;
DROP TABLE Ship_Type    CASCADE CONSTRAINTS PURGE;
DROP TABLE Engineer    CASCADE CONSTRAINTS PURGE;
DROP TABLE Captain    CASCADE CONSTRAINTS PURGE;
DROP TABLE Port    CASCADE CONSTRAINTS PURGE;
DROP TABLE Port_Manager    CASCADE CONSTRAINTS PURGE;
DROP TABLE Connection_Port    CASCADE CONSTRAINTS PURGE;
DROP TABLE Port_Staff    CASCADE CONSTRAINTS PURGE;
DROP TABLE Container CASCADE CONSTRAINTS PURGE;

create table Container(
container_id integer,
paylod float,
tare float,
gross float,
code_iso integer
);



create table ISO_Code(
code integer,
description varchar(100)
);

create table Refrigerated_Container(
temperature float,
container_id integer
);

create table Manifest(
id integer,
container_gross_weight float,
--fk
container_id integer
);



create table Manifest_Warehouse(
manifest_id integer,
warehouse_id integer
);



create table Warehouse(
id integer,
name varchar(100),
continent varchar(100),
country varchar(100),
location_name varchar(100),
warehouse_manager_id integer
);


create table Warehouse_Manager(
id integer,
name varchar(100)
);

create table Connection_Warehouse(
warehouse_staff_id integer,
warehouse_id integer
);


create table Warehouse_Staff(
id_staff integer,
name varchar(100)
);

create table Port_Manifest(
port_id integer,
manifest_id integer
);



create table Truck_Manifest(
truck_id integer,
manifest_id integer
);


create table Truck(
id integer,
description varchar (100),
driver_id integer
);



create table Driver(
id integer,
name varchar(100)
);

create table Ship_Manifest(
shipMMSI integer,
manifest_id integer,
container_position integer
);



create table Ship(
mmsi integer,
name varchar(100),
imo_number varchar(100),
generator_amount float,
power_output float,
callSign varchar(100),
lenght integer,
width integer,
capacity float,
--fk
ship_type_id integer,
fleet_id integer,
captain_id integer,
engineer_id integer

);



create table Ship_Status(
base_data_time varchar(100),
latitude float,
longitude float,
sog float,
cog float,
heading integer,
position integer,
transceiver_class varchar(100),
mmsi_ship integer
);



create table Fleet(
id integer,
manager_id integer
);

create table Fleet_Manager(
id integer,
name varchar(100)
);

create table Ship_Type(
id integer,
description varchar(100)
);

create table Engineer(
id integer,
name varchar(100)
);

create table Captain(
id integer,
name varchar(100)
);

create table Port(
id integer,
name varchar(100),
continent varchar(100),
country varchar(100),
latitude float,
longitude float,
port_manager_id integer
);



create table Port_Manager(
id integer,
name varchar(100)
);

create table Connection_Port(
port_id integer,
port_staff_id integer
);


create table Port_Staff(
id_Staff integer,
name varchar(100)
);


alter table Captain add constraint pk_id primary key (id);

alter table Engineer add constraint pk_id_engineer primary key(id);

alter table Ship_Type add constraint pk_id_type primary key(id);

alter table Fleet_Manager add constraint pk_id_manager_fleet primary key(id);

alter table Fleet add constraint pk__id_fleet primary Key(id);

alter table Fleet add constraint fk_manager_id foreign key(manager_id) references Fleet_Manager(id);

alter table Warehouse_Manager add constraint pk_warehouse_manager primary Key(id);

alter table Warehouse_Staff add constraint pk_id_Wstaff primary key(id_staff);

alter table Port_Staff add constraint pk_id_Pstaff primary key(id_Staff);

alter table Port_Manager add constraint pk_id_Pmanager primary key(id); 

alter table Driver add constraint pk_id_driver primary Key(id);

alter table Container add constraint pk_container_id primary key(container_id);
alter table ISO_code add constraint pk__iso_code primary key(code);
alter table Container add constraint fk_code_iso foreign key(code_iso) references ISO_code(code);
alter table Refrigerated_Container add constraint fk_container_id foreign key(container_id) references Container(container_id);
alter table Manifest add constraint pk_manifest_id primary key(id);
alter table Manifest add constraint fk__manifest_container_id foreign key(container_id) references Container(container_id);
alter table Manifest_Warehouse add constraint fk__manifestWarehouse_container_id foreign key(manifest_id) references Manifest(id);
alter table Warehouse add constraint pk_warehouse_id primary key(id);
alter table Manifest_Warehouse add constraint fk__manifestWarehouse_warehouse_id foreign key(warehouse_id) references Warehouse(id);
alter table Warehouse add constraint fk_Warehouse_manager_id foreign key(warehouse_manager_id) references Warehouse_Manager(id);
alter table Connection_Warehouse add constraint fk_Warehouse_Staff_id foreign key(warehouse_staff_id) references Warehouse_Staff(id_staff);
alter table Connection_Warehouse add constraint fk_Warehouse_Connection_id foreign key(warehouse_id) references Warehouse(id);
alter table Port add constraint pk_id_port primary key(id);
alter table Port add constraint fk_port_manager_id foreign key(port_manager_id) references Port_Manager(id);
alter table Port_Manifest add constraint pk_portManifest_port_id primary key(port_id);
alter table Port_Manifest add constraint fk__portManifest_port_id foreign key(port_id) references Port(id);
alter table Port_Manifest add constraint fk__portManifest_manifest_id foreign key(manifest_id) references Manifest(id);
alter table Truck add constraint pk_id_truck primary key(id);
alter table Truck add constraint fk_driver_id foreign key(driver_id) references Driver(id);
alter table Truck_Manifest add constraint pk_truckManifest_truck_id primary key(truck_id);
alter table Truck_Manifest add constraint fk__truckManifest_truck_id foreign key(truck_id) references Truck(id);
alter table Truck_Manifest add constraint fk__truckManifest_manifest_id foreign key(manifest_id) references Manifest(id);
alter table Ship add constraint pk_mmsi primary key(mmsi);
alter table Ship add constraint fk_ship_type_id foreign key(ship_type_id) references Ship_Type(id);
alter table Ship add constraint fk_fleet_id foreign key(fleet_id) references Fleet(id);
alter table Ship add constraint fk_captain_id foreign key(captain_id) references Captain(id);
alter table Ship add constraint fk_engineer_id foreign key(engineer_id) references Engineer(id);
alter table Ship_Manifest add constraint pk_shipManifest_mmsi_id primary key(shipMMSI);
alter table Ship_Manifest add constraint fk__shipManifest_mmsi_id foreign key(shipMMSI) references Ship(mmsi);
alter table Ship_Manifest add constraint fk__shipManifest_manifest_id foreign key(manifest_id) references Manifest(id);
alter table Ship_Status add constraint pk_base_date primary key(base_data_time);
alter table Ship_Status add constraint fk_ship_mmsi foreign key(mmsi_ship) references Ship(mmsi);
alter table Connection_Port add constraint fk_port_id foreign key(port_id) references Port(id);
alter table Connection_Port add constraint fk_port_Staff_id foreign key(port_staff_id) references Port_Staff(id_Staff);

--CHECK CONSTRAINTS--
ALTER TABLE Ship
ADD CONSTRAINT check_MMSI
  CHECK (mmsi > 99999999 AND mmsi < 1000000000);
  
ALTER TABLE Ship
ADD CONSTRAINT check_IMO
  CHECK (imo_number LIKE 'IMO%');
  
ALTER TABLE Ship_Status
ADD CONSTRAINT check_latitude
  CHECK (latitude BETWEEN -90 AND 90);
  
ALTER TABLE Ship_Status
ADD CONSTRAINT check_longitude
  CHECK (longitude BETWEEN -180 AND 180);
  
ALTER TABLE Port
ADD CONSTRAINT check_continent
  CHECK (continent IN ('Europe','Africa','America','Asia','Oceania'));
  
ALTER TABLE Port
ADD CONSTRAINT check_latitudePort
  CHECK (latitude BETWEEN -90 AND 90);
  
ALTER TABLE Port
ADD CONSTRAINT check_longitudePort
  CHECK (longitude BETWEEN -180 AND 180);
