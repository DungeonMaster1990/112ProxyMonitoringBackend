create table if not exists monitoring.updates
(
    id                  serial primary key,
    service_name        varchar,
    update_time         timestamp
);