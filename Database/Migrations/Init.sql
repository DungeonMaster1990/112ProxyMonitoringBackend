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

create sequence if not exists monitoring.plans_id_seq;
create table if not exists monitoring.plans
(
    id                         integer not null default nextval('monitoring.plans_id_seq'),
    foreignId                  varchar not null,
    plantypeId                 int     not null,
    phase                      int,
    status                     varchar,
    shortDescription           varchar,
    impactDescription          varchar,
    degradationRate            varchar,
    startDate                  timestamp,
    finishDate                 timestamp,
    startDownDate              timestamp,
    finishDownDate             timestamp,
    initiator                  varchar,
    customer                   varchar,
    owner                      varchar,
    implementationPlan         varchar,
    rollbackPlan               varchar,
    itSystems                  varchar,
    configurationUnits         varchar,
    affectedConfigurationUnits varchar,
    affectedItSystems          varchar,
    affectedItServices         varchar
);
alter sequence monitoring.plans_id_seq
owned by monitoring.plans.id;

create sequence if not exists monitoring.incidents_id_seq;
create table if not exists monitoring.incidents
(
    id                                   integer not null default nextval('monitoring.incidents_id_seq'),
    incident_id                          varchar not null,
    created_at                           timestamp,
    expired_at                           timestamp,
    author_id                            int,
    contact_id                           int,
    service_id                           int,
    category                             varchar,
    failure_point                        varchar,
    configuration_item_id                int,
    title                                varchar,
    description                          varchar,
    impact                               varchar,
    severity                             varchar,
    group_id                             int,
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
create table monitoring.unavailability
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
create table monitoring.pushTokens
(
    id         integer not null default nextval('monitoring.pushTokens_id_seq'),
    token      varchar,
    install_id varchar,
    platform   varchar
);
alter sequence monitoring.pushTokens_id_seq
owned by monitoring.pushTokens.id;

alter table  monitoring.pushTokens
add update_token_date timestamp;