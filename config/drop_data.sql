--------TRUNCATE TABLES AND RESTART SEQUENCES--------
TRUNCATE       changes RESTART IDENTITY;
ALTER SEQUENCE changes_id_seq RESTART WITH 1;
--------------------------------------------
TRUNCATE       unavailability RESTART IDENTITY;
ALTER SEQUENCE unavailability_id_seq RESTART WITH 1;
--------------------------------------------
TRUNCATE       pushtokens RESTART IDENTITY;
ALTER SEQUENCE pushtokens_id_seq RESTART WITH 1;
--------------------------------------------
TRUNCATE       incidents RESTART IDENTITY;
ALTER SEQUENCE incidents_id_seq RESTART WITH 1;


--------TRUNCATE TABLES AND RESTART SEQUENCES--------
UPDATE updates SET update_time = '2020-12-09 15:00:00', start_time = '2020-12-09 15:00:00'
WHERE service_name IN ('Unavailabilities', 'Incidents', 'Changes');