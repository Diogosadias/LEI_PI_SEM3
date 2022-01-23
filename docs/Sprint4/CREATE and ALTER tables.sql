-- APAGAR AS TABELAS--
DROP table ISO_Code Cascade constraints Purge;
DROP table Refrigerated_Container Cascade constraints Purge;
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
DROP TABLE Trip CASCADE CONSTRAINTS PURGE;
DROP TABLE Userperson CASCADE CONSTRAINTS PURGE;
DROP TABLE Employee CASCADE CONSTRAINTS PURGE;
DROP TABLE Role CASCADE CONSTRAINTS PURGE;
DROP TABLE Client CASCADE CONSTRAINTS PURGE;
DROP TABLE Cargo_Manifest CASCADE CONSTRAINTS PURGE;
DROP TABLE Container_Trip CASCADE CONSTRAINTS PURGE;
DROP TABLE Country CASCADE CONSTRAINTS PURGE;
DROP TABLE Border CASCADE CONSTRAINTS PURGE;
DROP TABLE Sea_Distance CASCADE CONSTRAINTS PURGE;
DROP TABLE Port_Sea_Distance CASCADE CONSTRAINTS PURGE;
DROP TABLE  Audit_Trail CASCADE CONSTRAINTS PURGE;

--CRIAR AS TABELAS--
create table Container(
container_id integer,
payload float NOT NULL,
tare float NOT NULL,
gross float NOT NULL,
code_iso varchar(100),
client_id integer,
port_id integer,
warehouse_id integer,
manifest_id integer
);

create table Client(
client_id integer,
email varchar(100) NOT NULL,
nif integer UNIQUE NOT NULL,
address varchar(100) NOT NULL,
phone_number varchar(9) UNIQUE NOT NULL
);

create table Container_Trip(
container_id integer,
trip_id integer,
x_coord integer NOT NULL,
y_coord integer NOT NULL,
z_coord integer NOT NULL
);
 
create table ISO_Code(
code varchar(100),
description varchar(100)
);

create table Refrigerated_Container(
temperature float NOT NULL,
container_id integer
);


create table Trip(
trip_id integer,
origin varchar(100) NOT NULL,
destination varchar(100) NOT NULL,
base_date_time_origin varchar(255) NOT NULL,
base_date_time_end varchar(255) NOT NULL,
truck_id integer,
mmsi integer
);

create table Warehouse(
warehouse_id integer,
name varchar(100),
continente varchar(100) NOT NULL,
country varchar(100) NOT NULL,
location varchar(100) NOT NULL,
capacity integer NOT NULL
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
name varchar(100) NOT NULL
);

create table Ship(
mmsi integer,
name varchar(100),
imo_number varchar(100) UNIQUE NOT NULL,
generator_amount float,
power_output float,
callSign varchar(100) UNIQUE,
lenght integer,
width integer,
capacity float NOT NULL,
--fk
ship_type_id integer,
fleet_id integer,
captain_id integer
);

create table Ship_Status(
base_date_time varchar(100),
latitude float NOT NULL,
longitude float NOT NULL,
sog float,
cog float,
heading integer,
transceiver_class varchar(100),
ship_mmsi integer
);

create table Cargo_Manifest(
manifest_id integer,
base_date_time varchar(100) NOT NULL,
container_gross_weight float,
type varchar(100),
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
email varchar(100) NOT NULL,
name varchar(100) NOT NULL,
phone_number varchar(9) UNIQUE NOT NULL,
address varchar(100) NOT NULL
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
name varchar(100) NOT NULL,
phone_number varchar(9) UNIQUE NOT NULL,
email varchar(100) NOT NULL
);

create table Port(
port_id integer,
name varchar(100),
continent varchar(100) NOT NULL,
country_name varchar(100) NOT NULL,
location varchar(100) NOT NULL,
latitude float NOT NULL,
longitude float NOT NULL,
capacity integer NOT NULL
);

create table Port_Employee(
port_id integer,
employee_id integer
);

create table Userperson(
email varchar(100),
password varchar(16)
);

create table Country(
name varchar(100),
alpha2_code varchar(100),
alpha3_code varchar(100),
continent varchar(100) NOT NULL,
population float NOT NULL,
capital varchar(100) NOT NULL,
latitude float NOT NULL,
longitude float NOT NULL
);

create table Border(
Countryname varchar(100),
country2 varchar(100)
);

create table Sea_Distance(
id integer,
from_Country varchar(100),
from_Port_id integer,
from_Port_name varchar(100),
to_Country varchar(100),
to_Port_id integer,
to_Port_name varchar(100),
sea_distance integer
);

create table Port_Sea_Distance(
port_id integer,
sea_distance_id integer
);

create table Audit_Trail(
audit_trail_id integer,
container_id integer,
user_email varchar(100),
date_time_registered varchar(100),
type_of_operation varchar(100)
);

-- DEFINIR AS PRIMARY KEYS--
alter table Captain add constraint pk_captain_id primary key (captain_id);
alter table Ship_Type add constraint pk_id_type primary key(type_id);
alter table Fleet add constraint pk_id_fleet primary Key(fleet_id);
alter table Driver add constraint pk_id_driver primary Key(driver_id);
alter table Container add constraint pk_container_id primary key(container_id);
alter table ISO_code add constraint pk_iso_code primary key(code);
alter table Trip add constraint pk_trip_id primary key(trip_id);
alter table Warehouse add constraint pk_warehouse_id primary key(warehouse_id);
alter table Port add constraint pk_id_port primary key(port_id);
alter table Truck add constraint pk_id_truck primary key(truck_id);
alter table Ship add constraint pk_mmsi primary key(mmsi);
alter table Ship_Status add constraint pk_base_date primary key(base_date_time);
alter table Client add constraint pk_client_id primary key(client_id);
alter table Userperson add constraint pk_email primary key(email);
alter table Role add constraint pk_role_id primary key(role_id);
alter table Employee add constraint pk_employee_id primary key(employee_id);
alter table Cargo_Manifest add constraint pk_manifest_id primary key(manifest_id);
alter table Country add constraint pk_country_id primary key(name);
alter table Sea_Distance add constraint pk_sea_distance primary key(id);
alter table Container_Trip add constraint pk_container_trip_container_trip_id primary key(container_id, trip_id);
alter table Audit_Trail add constraint pk_audit_trail_id primary key(audit_trail_id);

--DEFINIR AS FOREIGN KEYS--
alter table Container add constraint fk_code_iso foreign key(code_iso) references ISO_code(code);
alter table Refrigerated_Container add constraint fk_container_id foreign key(container_id) references Container(container_id);
alter table Truck add constraint fk_driver_id foreign key(driver_id) references Driver(driver_id);
alter table Ship add constraint fk_ship_type_id foreign key(ship_type_id) references Ship_Type(type_id);
alter table Ship add constraint fk_fleet_id foreign key(fleet_id) references Fleet(fleet_id);
alter table Ship add constraint fk_captain_id foreign key(captain_id) references Captain(captain_id);
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
alter table Captain add constraint fk_captain_email foreign key(email) references Userperson(email); 
alter table Container_Trip add  constraint fk_container_trip_id foreign key(container_id) references Container(container_id);
alter table Container_Trip add constraint fk_container_trip_trip_id foreign key(trip_id) references Trip(trip_id);
alter table Container add constraint fk_container_port_id foreign key(port_id) references Port(port_id);
alter table Container add constraint fk_container_warehouse_id foreign key(warehouse_id) references Warehouse(warehouse_id);
alter table Container add constraint fk_container_manifest_id foreign key(manifest_id) references Cargo_Manifest(manifest_id);
alter table Port add constraint fk_port_country_id foreign key(country_name) references Country(name);
alter table Border add constraint fk_border_country foreign key(Countryname) references Country(name);
alter table Port_Sea_Distance add constraint fk_port_sea_port_id foreign key(port_id) references Port(port_id);
alter table Port_Sea_Distance add constraint fk_port_sea_distance_id foreign key(sea_distance_id) references Sea_Distance(id);
alter table Audit_Trail add constraint fk_audit_trail_container_id foreign key(container_id) references Container(container_id);
alter table Cargo_Manifest add constraint fk_manifest_trip_id foreign key(trip_id) references Trip(trip_id);
alter table Trip add constraint fk_trip_mmsi foreign key(mmsi) references Ship(mmsi);
alter table Trip add constraint fk_trip_truck_id foreign key(truck_id) references Truck(truck_id);

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