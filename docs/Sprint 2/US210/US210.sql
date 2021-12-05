create or replace function nextMonday(p_date_time in varchar) return SYS_REFCURSOR
as
next_monday SYS_REFCURSOR;
date_next_monday date;

BEGIN 
  OPEN next_monday FOR
    SELECT ship_mmsi, latitude, longitude 
    from Ship_Status where '2021.12.06' = (select next_day(p_date_time,'Segunda-Feira')
     FROM DUAL); 
--select convert(varchar, getdate(), 1)
  RETURN next_monday;

EXCEPTION
WHEN OTHERS THEN
   return null;
END;

select nextMonday('2021.12.05') from dual;