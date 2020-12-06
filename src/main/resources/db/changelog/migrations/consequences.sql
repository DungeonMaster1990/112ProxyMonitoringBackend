alter table monitoring.incidents
    add if not exists manager_id varchar null;
alter table monitoring.incidents
    add if not exists consequences varchar null;
alter table monitoring.incidents
    add if not exists elimination_consequences_at timestamp null;
alter table monitoring.incidents
    drop if exists type;

update monitoring.incidents
set manager_id = specialist_id
where specialist_id is not null;