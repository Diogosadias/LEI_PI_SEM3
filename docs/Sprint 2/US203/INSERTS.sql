INSERT INTO Userperson(email, password) VALUES('client@email.com', 'client2021');
INSERT INTO Userperson(email, password) VALUES('username@email.com', 'qwerty2021');
INSERT INTO Userperson(email, password) VALUES('staffname@email.com', 'qwerty2021');
INSERT INTO Userperson(email, password) VALUES('mangername@email.com', 'qwerty2021');
INSERT INTO Userperson(email, password) VALUES('captain@email.com', 'captain2021');

INSERT INTO Client(client_id, email, nif, address, phone_number) VALUES(1, 'client@email.com', 255123456, 'Rua Portas Água 73, 2490-677, Ourém', '912345678');

insert into iso_code(code, description) values('20GP', 'GENERAL PURPOSE CONTAINER');
insert into iso_code(code, description) values('20HR','INSULATED CONTAINER');
insert into iso_code(code, description) values('20PF', 'FLAT (FIXED ENDS)');
insert into iso_code(code, description) values('20TD','TANK CONTAINER');
insert into iso_code(code, description) values('20TG','TANK CONTAINER');
insert into iso_code(code, description) values('20TN','TANK CONTAINER');
insert into iso_code(code, description) values('22BU', 'BULK CONTAINER');
insert into iso_code(code, description) values('22GP', 'GENERAL PURPOSE CONT.');
insert into iso_code(code, description) values('22HR', 'INSULATED CONTAINER');
insert into iso_code(code, description) values('22PC', 'FLAT (COLLAPSIBLE)');
insert into iso_code(code, description) values('22PF','FLAT (FIXED ENDS)');
insert into iso_code(code, description) values('22RC','REEFER CONT.(NO FOOD)');
insert into iso_code(code, description) values('22RS', 'BUILT-IN GEN. F. POWER SPLY OF REEF');
insert into iso_code(code, description) values('22RT', 'REEFER CONTAINER');
insert into iso_code(code, description) values('20G0', 'GENERAL PURPOSE CONT.');
insert into iso_code(code, description) values('20G1', 'GENERAL PURPOSE CONT.');
insert into iso_code(code, description) values('20H0', 'INSULATED CONTAINER');
insert into iso_code(code, description) values('20P1', 'FLAT (FIXED ENDS)');
insert into iso_code(code, description) values('20T3', 'TANK CONTAINER');
insert into iso_code(code, description) values('20T4', 'TANK CONTAINER');
insert into iso_code(code, description) values('20T5', 'TANK CONTAINER');
insert into iso_code(code, description) values('20T6', 'TANK CONTAINER');
insert into iso_code(code, description) values('20T7', 'TANK CONTAINER');
insert into iso_code(code, description) values('20T8', 'TANK CONTAINER');
insert into iso_code(code, description) values('20T0','TANK CONTAINER');
insert into iso_code(code, description) values('22B0', 'BULK CONTAINER');

insert into Port(port_id, name, continent, country, location, latitude, longitude) values(11111, 'port1', 'America', 'Canada','CanadaPort', -18,-82.1);
insert into Port(port_id, name, continent, country, location, latitude, longitude) values(11112, 'port2', 'America', 'Brazil','BrazilPort', -19,-85.1111);
insert into Port(port_id, name, continent, country, location, latitude, longitude) values(11113, 'port3', 'Europe', 'Portugal','PortugalPort', 45.183333,-9.7);
insert into Port(port_id, name, continent, country, location, latitude, longitude) values(11114, 'port4', 'Oceania', 'Australia','AustraliaPort', 25.23,135.45);
insert into Port(port_id, name, continent, country, location, latitude, longitude) values(11115, 'port5', 'Oceania', 'New Zealand','NewZealandPort', 54.89,48.11);
insert into Port(port_id, name, continent, country, location, latitude, longitude) values(11116, 'port6', 'Oceania', 'Fiji','FijiPort', -17.7133,178.065);

insert into Container(container_id, payload, tare, gross, code_iso,client_id,port_id) values(11, 33.2, 45.0, 55.0, '20GP', 1,11111);
insert into Container(container_id, payload, tare, gross, code_iso, client_id,port_id) values(12, 333.2, 455.0, 555.0, '20GP', 1,11111);
insert into Container(container_id, payload, tare, gross, code_iso,client_id,port_id) values(13, 33.2, 135.0, 255.0, '20T8', 1,11112);
insert into Container(container_id, payload, tare, gross, code_iso,client_id,port_id) values(14, 433.2, 455.0, 300.0, '20GP', 1,11112);
insert into Container(container_id, payload, tare, gross, code_iso,client_id,port_id) values(15, 100, 500, 400, '20T7', 1,11113);
insert into Container(container_id, payload, tare, gross, code_iso, client_id,port_id) values(16, 200, 100, 400, '20T6', 1,11113);
insert into Container(container_id, payload, tare, gross, code_iso, client_id,port_id) values(17, 333.2, 455.0, 765.0, '20T7', 1,11114);
insert into Container(container_id, payload, tare, gross, code_iso,client_id,port_id) values(18, 333.2, 455.0, 555.0, '20T8', 1,11114);
insert into Container(container_id, payload, tare, gross, code_iso, client_id,port_id) values(19, 900.9, 45.0, 55.0, '20TN', 1,11115);
insert into Container(container_id, payload, tare, gross, code_iso, client_id,port_id) values(20, 876.2, 378.67, 321.8, '20TD', 1,11115);
insert into Container(container_id, payload, tare, gross, code_iso, client_id,port_id) values(21, 243.2, 654.98, 987.89, '20HR', 1,11116);
insert into Container(container_id, payload, tare, gross, code_iso, client_id,port_id) values(22, 321.2, 654.5, 876.56, '20HR', 1,11116);


insert into Manifest_Load(manifest_load_id, base_date_time, container_gross_weight) values(70, '02/02/2019 08:00', 400.89);
insert into Manifest_Load(manifest_load_id, base_date_time, container_gross_weight) values(71, '03/02/2019 10:00', 567.89);
insert into Manifest_Load(manifest_load_id, base_date_time, container_gross_weight) values(72, '04/02/2019 00:00', 234.89);
insert into Manifest_Load(manifest_load_id, base_date_time, container_gross_weight) values(73, '05/02/2019 16:00', 100.5);

insert into Manifest_Unload(manifest_unload_id, base_date_time) values(55,'02/02/2019 23:00');
insert into Manifest_Unload(manifest_unload_id, base_date_time) values(56,'03/02/2019 23:00');
insert into Manifest_Unload(manifest_unload_id, base_date_time) values(57,'04/03/2019 10:00');
insert into Manifest_Unload(manifest_unload_id, base_date_time) values(58,'05/03/2019 10:00');

insert into Trip(trip_id, origin, destination, base_date_time_origin) values(50, 'Port1', 'Port2', '02/02/2019 08:00');
insert into Trip(trip_id, origin, destination, base_date_time_origin) values(51, 'Port2', 'Port3', '03/02/2019 10:00');
insert into Trip(trip_id, origin, destination, base_date_time_origin) values(52,  'Port3', 'Port4', '03/02/2020 10:00');
insert into Trip(trip_id, origin, destination, base_date_time_origin) values(55, 'Port6', 'Port5', '10/05/2020 10:00');

insert into Container_Trip(container_id, trip_id,manifest_load,manifest_unload,x_coord,y_coord,z_coord) values(11,50,70,55, 1, 1, 1);
insert into Container_Trip(container_id, trip_id,manifest_load,manifest_unload,x_coord,y_coord,z_coord) values(12,50,70,null, 1, 5, 10);
insert into Container_Trip(container_id, trip_id,manifest_load,manifest_unload,x_coord,y_coord,z_coord) values(13,50,70,55, 5, 2, 1);
insert into Container_Trip(container_id, trip_id,manifest_load,manifest_unload,x_coord,y_coord,z_coord) values(14,50,70,null, 5, 5, 10);


insert into Container_Trip(container_id, trip_id,manifest_load,manifest_unload,x_coord,y_coord,z_coord) values(15,51,71,null, 10, 10, 10);
insert into Container_Trip(container_id, trip_id,manifest_load,manifest_unload,x_coord,y_coord,z_coord) values(17,51,71,null, 1, 5, 10);
insert into Container_Trip(container_id, trip_id,manifest_load,manifest_unload,x_coord,y_coord,z_coord) values(18,51,71,null, 1, 0, 1);
insert into Container_Trip(container_id, trip_id,manifest_load,manifest_unload,x_coord,y_coord,z_coord) values(16,51,71,null, 6, 5, 10);



insert into Driver(driver_id, name) values(10, 'driver1');
insert into Driver(driver_id, name) values(11, 'driver2');

insert into Truck(truck_id, description, driver_id) values(30, 'truck1', 10);
insert into Truck(truck_id, description, driver_id) values(31, 'truck2', 11);

insert into Trip_Truck(trip_id, truck_id) values(58, 30);
insert into Trip_Truck(trip_id, truck_id) values(52, 31);


insert into Warehouse(warehouse_id, name, continente, country, location) values(80, 'warehouse1', 'America', 'Canada','CanadaWarehouse');
insert into Warehouse(warehouse_id, name, continente, country, location) values(81, 'warehouse2', 'America', 'Brazil','BrazilWarehouse');
insert into Warehouse(warehouse_id, name, continente, country, location) values(82, 'warehouse3', 'Europe', 'Portugal','PortugalWarehouse');
insert into Warehouse(warehouse_id, name, continente, country, location) values(83, 'warehouse4', 'Oceania', 'Australia','AustraliaWarehouse');

insert into Role(role_id, name) values(1, 'Staff');
insert into Role(role_id, name) values(2, 'Manager');

insert into Employee(employee_id, manager_id, role_id, email, name, phone_number, address) values(60, 60, 1, 'mangername@email.com', 'Roberto', '934567898','Rua branca 7, 4000-123');
insert into Employee(employee_id, manager_id, role_id, email, name, phone_number, address) values(61, 61, 1, 'staffname@email.com', 'Leandro', '914557878', 'Rua da Formosa 66, 4567-008');

insert into Employee(employee_id, manager_id, role_id, email, name, phone_number, address) values(62, 62, 1, 'mangername@email.com', 'Jeogirna', '924567898','Rua das caldas 67, 4123-123');
insert into Employee(employee_id, manager_id, role_id, email, name, phone_number, address) values(63, 63, 1, 'staffname@email.com', 'Matilde', '964557878', 'Rua da Formosa 66, 4765-010');

insert into Employee(employee_id, manager_id, role_id, email, name, phone_number, address) values(64, 64, 1, 'mangername@email.com', 'João', '924007898','Rua das caldas 67, 4123-300');
insert into Employee(employee_id, manager_id, role_id, email, name, phone_number, address) values(65, 65, 1, 'staffname@email.com', 'Diogo', '964597800', 'Rua da Formosa 66, 4766-100');

insert into Warehouse_Employee(employee_id, warehouse_id) values(60, 80);
insert into Warehouse_Employee(employee_id, warehouse_id) values(61, 80);
insert into Warehouse_Employee(employee_id, warehouse_id) values(60, 81);
insert into Warehouse_Employee(employee_id, warehouse_id) values(61, 81);
insert into Warehouse_Employee(employee_id, warehouse_id) values(60, 82);
insert into Warehouse_Employee(employee_id, warehouse_id) values(61, 82);
insert into Warehouse_Employee(employee_id, warehouse_id) values(60, 83);
insert into Warehouse_Employee(employee_id, warehouse_id) values(61, 83);

insert into Port_Employee(port_id, employee_id) values(11111,62);
insert into Port_Employee(port_id, employee_id) values(11111,63);
insert into Port_Employee(port_id, employee_id) values(11112,62);
insert into Port_Employee(port_id, employee_id) values(11112,63);
insert into Port_Employee(port_id, employee_id) values(11113,62);
insert into Port_Employee(port_id, employee_id) values(11113,63);
insert into Port_Employee(port_id, employee_id) values(11114,62);
insert into Port_Employee(port_id, employee_id) values(11114,63);
insert into Port_Employee(port_id, employee_id) values(11115,62);
insert into Port_Employee(port_id, employee_id) values(11115,63);
insert into Port_Employee(port_id, employee_id) values(11116,62);
insert into Port_Employee(port_id, employee_id) values(11116,63);

insert into Fleet(fleet_id, name) values(40, 'fleet1');
insert into Fleet(fleet_id, name) values(41, 'fleet2');
insert into Fleet(fleet_id, name) values(42, 'fleet3');

insert into Fleet_Employee(fleet_id, employee_id) values(40, 65);
insert into Fleet_Employee(fleet_id, employee_id) values(41, 65);
insert into Fleet_Employee(fleet_id, employee_id) values(42, 65);

insert into Captain(captain_id, name, phone_number, email) values(90, 'captain1', '976543321', 'captain@email.com');
insert into Captain(captain_id, name, phone_number, email) values(91, 'captain2', '970503321', 'captain@email.com');

insert into Ship_Type(type_id, description) values(1, 'Brigue');
insert into Ship_Type(type_id, description) values(2, 'Fragata');
insert into Ship_Type(type_id, description) values(3, 'Nau');

insert into Ship(mmsi, name, imo_number, generator_amount, power_output, callsign, lenght, width, capacity, ship_type_id, fleet_id, captain_id) values(999999999,'ship1','IMO2345699',78.90,50,'X4SQ2', 166,25,9.5, 1, 40, 90);
insert into Ship(mmsi, name, imo_number, generator_amount, power_output, callsign, lenght, width, capacity, ship_type_id, fleet_id, captain_id) values(888888888,'ship2','IMO2347790',80.90,70,'Y4SW2', 176,35,10.5, 2, 41, 91);

insert into Ship_Status(base_date_time, latitude, longitude, sog, cog, heading, transceiver_class, ship_mmsi) values('31/01/2019 17:19', -66.97001,12.9,13.1,355,300,'B', 999999999);
insert into Ship_Status(base_date_time, latitude, longitude, sog, cog, heading, transceiver_class, ship_mmsi) values('31/01/2019 23:19', -30.97001,30.9,15.1,370,300,'B', 999999999);

insert into Ship_Status(base_date_time, latitude, longitude, sog, cog, heading, transceiver_class, ship_mmsi) values('31/02/2019 17:19', -66.97001,12.9,13.1,255,20,'B', 888888888);
insert into Ship_Status(base_date_time, latitude, longitude, sog, cog, heading, transceiver_class, ship_mmsi) values('31/02/2019 23:19', -30.97001,30.9,15.1,270,20,'B', 888888888);

insert into Trip_Ship(trip_id, ship_mmsi) values(50, 999999999);
insert into Trip_Ship(trip_id, ship_mmsi) values(51, 999999999);
insert into Trip_Ship(trip_id, ship_mmsi) values(53, 999999999);

insert into Trip_Ship(trip_id, ship_mmsi) values(54, 888888888);
insert into Trip_Ship(trip_id, ship_mmsi) values(55, 888888888);


