create schema if not exists monitoring;

create table if not exists  monitoring.accidentStatuses
(
    id   serial primary key,
    name varchar
);
create table if not exists monitoring.failureStatuses
(
    id         serial primary key,
    name       varchar,
    accidentId int
);

create sequence if not exists monitoring.systems_id_seq;
create table if not exists monitoring.systems
(
    id         integer not null default nextval('monitoring.systems_id_seq'),
    name       varchar,
    accidentId int
);
alter sequence monitoring.systems_id_seq
owned by monitoring.systems.id;

create table if not exists monitoring.plantypes
(
    id   serial primary key,
    name varchar
);

create sequence if not exists monitoring.incidents_id_seq;
create table if not exists monitoring.incidents
(
    id                                   integer not null default nextval('monitoring.incidents_id_seq'),
    incident_id                          varchar not null,
    created_at                           timestamp,
    expired_at                           timestamp,
    author_id                            varchar,
    contact_id                           varchar,
    service_id                           varchar,
    category                             varchar,
    failure_point                        varchar,
    configuration_item_id                varchar,
    title                                varchar,
    description                          varchar,
    impact                               varchar,
    severity                             varchar,
    group_id                             varchar,
    assignee_id                          varchar,
    status                               varchar,
    close_code                           varchar,
    resolution                           varchar,
    comment                              varchar,
    updated_at                           timestamp,
    type                                 varchar,
    sla_start_time                       timestamp,
    jira_number                          varchar,
    template_name                        varchar,
    ext_organization                     varchar,
    ext_id                               varchar,
    ext_status                           varchar,
    ext_assignee_time                    varchar,
    source                               varchar,
    specialist_id                        varchar,
    priority                             varchar,
    idented_at                           timestamp,
    fact_end_at                          timestamp,
    fact_begin_at                        timestamp
);

alter sequence monitoring.incidents_id_seq
owned by monitoring.incidents.id;

create sequence if not exists monitoring.unavailability_id_seq;
create table if not exists monitoring.unavailability
(
    id              integer not null default nextval('monitoring.unavailability_id_seq'),
    fault_id        varchar,
    begin_at        timestamp,
    end_at          timestamp,
    duration        interval,
    service_name    varchar,
    type            varchar,
    service_id      varchar,
    created_at      varchar,
    created_by_id   varchar,
    updated_at      timestamp,
    updated_by_id   varchar
);

alter sequence monitoring.unavailability_id_seq
owned by monitoring.unavailability.id;

create sequence if not exists monitoring.pushTokens_id_seq;
create table if not exists monitoring.pushTokens
(
    id         integer not null default nextval('monitoring.pushTokens_id_seq'),
    token      varchar,
    install_id varchar,
    platform   varchar,
    update_token_date timestamp
);
alter sequence monitoring.pushTokens_id_seq
owned by monitoring.pushTokens.id;