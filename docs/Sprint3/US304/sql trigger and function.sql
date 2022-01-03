create or replace function getAuditTrail(p_containerID integer, p_manifestID integer)
Return SYS_REFCURSOR
AS

audit_trail SYS_REFCURSOR;


BEGIN

OPEN audit_trail FOR
--INFORMAÇAO A APRESENTAR user(audit trail), the date and time(audit trail), the type of operation(audit trail), container id(audit trail) and manifest id(Container_trip)
SELECT Audit_Trail.user_email, Audit_Trail.date_time_registered,  Audit_Trail.type_of_operation, Audit_Trail.container_id, Container_Trip.manifest_load, Container_Trip.manifest_unload
FROM Audit_Trail 
INNER JOIN Container_Trip  on Container_Trip.container_id = Audit_Trail.container_id 
WHERE Container_Trip.container_id = p_containerID and Audit_Trail.container_id = p_containerID and Container_Trip.manifest_load = p_manifestID or Container_Trip.manifest_unload = p_manifestID
ORDER BY Audit_Trail.date_time_registered;

Return audit_trail;
        
END;


SELECT getAuditTrail(13, 71) FROM DUAL;

create sequence audit_trail_id start with 1 increment by 1;



create or replace trigger insert_operation 
after insert or delete or UPDATE on Container_Trip 
for each row
begin
if INSERTING then
insert into Audit_Trail values(audit_trail_id.nextval,:new.container_id, USER, LOCALTIMESTAMP, 'Insert');
else if DELETING then
insert into Audit_Trail values(audit_trail_id.nextval,:old.container_id, USER, LOCALTIMESTAMP, 'Delete');
else if UPDATING then
insert into Audit_Trail values(audit_trail_id.nextval,:new.container_id, USER, LOCALTIMESTAMP, 'Update');
end if;
end if;
end if;

end;




