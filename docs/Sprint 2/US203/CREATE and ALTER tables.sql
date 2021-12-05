-- APAGAR AS TABELAS--
DROP table ISO_Code Cascade constraints Purge;
DROP table Refrigerated_Container Cascade constraints Purge;
DROP table Manifest Cascade constraints Purge;
DROP TABLE Warehouse   CASCADE CONSTRAINTS PURGE;
DROP TABLE Warehouse_Employee   CASCADE CONSTRAINTS PURGE;
DROP TABLE Truck        CASCADE CONSTRAINTS PURGE;
DROP TABLE Driver        CASCADE CONSTRAINTS PURGE;
DROP TABLE Ship    CASCADE CONSTRAINTS PURGE;
DROP TABLE Ship_Status    CASCADE CONSTRAINTS PURGE;
DROP TABLE Fleet    CASCADE CONSTRAINTS PURGE;
DROP TABLE Fleet_Employee    CASCADE CONSTRAINTS PURGE;
DROP TABLE Ship_Type    CASCADE CONSTRAINTS PURGE;
DROP TABLE Captain    CASCADE CONSTRAINTS PURGE;
DROP TABLE Port    CASCADE CONSTRAINTS PURGE;
DROP TABLE Port_Employee    CASCADE CONSTRAINTS PURGE;
DROP TABLE Container CASCADE CONSTRAINTS PURGE;
DROP TABLE Container_Manifest CASCADE CONSTRAINTS PURGE;
DROP TABLE Trip CASCADE CONSTRAINTS PURGE;
DROP TABLE Trip_Truck CASCADE CONSTRAINTS PURGE;
DROP TABLE Trip_Ship CASCADE CONSTRAINTS PURGE;
DROP TABLE Userperson CASCADE CONSTRAINTS PURGE;
DROP TABLE Employee CASCADE CONSTRAINTS PURGE;
DROP TABLE Role CASCADE CONSTRAINTS PURGE;
DROP TABLE Client CASCADE CONSTRAINTS PURGE;
DROP TABLE Manifest_Unload CASCADE CONSTRAINTS PURGE;
DROP TABLE Manifest_Load CASCADE CONSTRAINTS PURGE;

--CRIAR AS TABELAS--
create table Container(
container_id integer,
payload float,
tare float,
gross float,
code_iso varchar(100),
port_id integer,
client_id integer
);

create table Client(
client_id integer,
email varchar(100),
nif integer UNIQUE,
address varchar(100),
phone_number varchar(9) UNIQUE
);

create table Container_Manifest(
container_id integer,
manifest_id integer,
x_coord integer,
y_coord integer,
z_coord integer
);

create table ISO_Code(
code varchar(100),
description varchar(100)
);

create table Refrigerated_Container(
temperature float,
container_id integer
);

create table Manifest(
manifest_id integer,
container_gross_weight float
);

create table Trip(
trip_id integer,
container_manifest_container_id integer,
origin varchar(100),
destination varchar(100)
);

create table Trip_Truck(
trip_id integer,
truck_id integer
);

create table Trip_Ship(
trip_id integer,
ship_mmsi integer
);

create table Warehouse(
warehouse_id integer,
name varchar(100),
continente varchar(100),
country varchar(100),
location varchar(100)
);

create table Warehouse_Employee(
employee_id integer,
warehouse_id integer
);

create table Truck(
truck_id integer,
description varchar (100),
driver_id integer
);

create table Driver(
driver_id integer,
name varchar(100)
);

create table Ship(
mmsi integer,
name varchar(100),
imo_number varchar(100) UNIQUE,
generator_amount float,
power_output float,
callSign varchar(100) UNIQUE,
lenght integer,
width integer,
capacity float,
--fk
ship_type_id integer,
fleet_id integer,
captain_id integer
);

create table Ship_Status(
base_date_time varchar(100),
latitude float,
longitude float,
sog float,
cog float,
heading integer,
transceiver_class varchar(100),
ship_mmsi integer
);

create table Manifest_Unload(
manifest_unload_id integer,
base_date_time varchar(100),
trip_id integer
);

create table Manifest_Load(
manifest_load_id integer,
base_date_time varchar(100),
trip_id integer
);

create table Fleet(
fleet_id integer,
name varchar(100)
);

create table Fleet_Employee(
fleet_id integer,
employee_id integer
);

create table Employee(
employee_id integer,
manager_id integer,
role_id integer,
email varchar(100),
name varchar(100),
phone_number varchar(9) UNIQUE,
address varchar(100)
);

create table Role(
role_id integer,
name varchar(100)
);

create table Ship_Type(
type_id integer,
description varchar(100)
);

create table Captain(
captain_id integer,
name varchar(100),
phone_number varchar(9) UNIQUE,
email varchar(100)
);

create table Port(
port_id integer,
name varchar(100),
continent varchar(100),
country varchar(100),
location varchar(100),
latitude float,
longitude float
);

create table Port_Employee(
port_id integer,
employee_id integer
);

create table Userperson(
email varchar(100),
password varchar(16)
);

-- DEFINIR AS PRIMARY KEYS--
alter table Captain add constraint pk_captain_id primary key (captain_id);
alter table Ship_Type add constraint pk_id_type primary key(type_id);
alter table Fleet add constraint pk_id_fleet primary Key(fleet_id);
alter table Driver add constraint pk_id_driver primary Key(driver_id);
alter table Container add constraint pk_container_id primary key(container_id);
alter table ISO_code add constraint pk_iso_code primary key(code);
alter table Manifest add constraint pk_manifest_id primary key(manifest_id);
alter table Trip add constraint pk_trip_id primary key(trip_id);
alter table Container_Manifest add constraint pk_containerid primary key(container_id);
alter table Warehouse add constraint pk_warehouse_id primary key(warehouse_id);
alter table Port add constraint pk_id_port primary key(port_id);
alter table Truck add constraint pk_id_truck primary key(truck_id);
alter table Ship add constraint pk_mmsi primary key(mmsi);
alter table Ship_Status add constraint pk_base_date primary key(base_date_time);
alter table Client add constraint pk_client_id primary key(client_id);
alter table Userperson add constraint pk_email primary key(email);
alter table Role add constraint pk_role_id primary key(role_id);
alter table Employee add constraint pk_employee_id primary key(employee_id);
alter table Manifest_Unload add constraint pk_manifest_unload_id primary key(manifest_unload_id);
alter table Manifest_Load add constraint pk_manifest_load_id primary key(manifest_load_id);

--DEFINIR AS FOREIGN KEYS--
alter table Container add constraint fk_code_iso foreign key(code_iso) references ISO_code(code);
alter table Refrigerated_Container add constraint fk_container_id foreign key(container_id) references Container(container_id);
alter table Container_Manifest add constraint fk_containerid foreign key(container_id) references Container(container_id);
alter table Container_Manifest add constraint fk_manifest_id foreign key(manifest_id) references Manifest(manifest_id);
alter table Trip add constraint fk_container_manifest_container_id foreign key(container_manifest_container_id) references Container_Manifest(container_id);
alter table Container add constraint fk_port_id foreign key(port_id) references Port(port_id);
alter table Truck add constraint fk_driver_id foreign key(driver_id) references Driver(driver_id);
alter table Trip_Truck add constraint fk_truck_id foreign key(truck_id) references Truck(truck_id);
alter table Trip_Truck add constraint fk_tripT_id foreign key(trip_id) references Trip(trip_id);
alter table Ship add constraint fk_ship_type_id foreign key(ship_type_id) references Ship_Type(type_id);
alter table Ship add constraint fk_fleet_id foreign key(fleet_id) references Fleet(fleet_id);
alter table Ship add constraint fk_captain_id foreign key(captain_id) references Captain(captain_id);
alter table Trip_Ship add constraint fk_shipT_mmsi foreign key(ship_mmsi) references Ship(mmsi);
alter table Trip_Ship add constraint fk_tripS_id foreign key(trip_id) references Trip(trip_id);
alter table Ship_Status add constraint fk_shipS_mmsi foreign key(ship_mmsi) references Ship(mmsi);
alter table Client add constraint fk_email foreign key(email) references Userperson(email);
alter table Container add constraint fk_client_id foreign key(client_id) references Client(client_id);
alter table Employee add constraint fk_role_id foreign key(role_id) references Role(role_id);
alter table Employee add constraint fk_manager_id foreign key(manager_id) references Employee(employee_id);
alter table Warehouse_Employee add constraint fk_warehouse_id foreign key(warehouse_id) references Warehouse(warehouse_id);
alter table Warehouse_Employee add constraint fk_employee_id foreign key(employee_id) references Employee(employee_id);
alter table Port_Employee add constraint fk_portE_id foreign key(port_id) references Port(port_id);
alter table Port_Employee add constraint fk_employeeP_id foreign key(employee_id) references Employee(employee_id);
alter table Fleet_Employee add constraint fk_employeeF_id foreign key(employee_id) references Employee(employee_id);
alter table Fleet_Employee add constraint fk_fleetE_id foreign key(fleet_id) references Fleet(fleet_id);
alter table Manifest_Unload add constraint fk_manifestU_trip_id foreign key(trip_id ) references Trip(trip_id);
alter table Manifest_Load add constraint fk_manifestL_trip_id foreign key(trip_id ) references Trip(trip_id);
alter table Captain add constraint fk_captain_email foreign key(email) references Userperson(email); 

--RESTRIÇÕES--

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
  
ALTER TABLE Ship
ADD CONSTRAINT check_lenght
    CHECK (lenght > 0);

ALTER TABLE Ship
ADD CONSTRAINT check_width
    CHECK (width > 0);

ALTER TABLE Container
ADD CONSTRAINT check_gross
    CHECK (gross > 0);
