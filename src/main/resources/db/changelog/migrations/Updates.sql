drop sequence if exists monitoring.updates_id_seq;
drop table if exists monitoring.updates;
create table monitoring.updates
(
    id                  serial primary key,
    service_name        varchar,
    update_time         timestamp
);