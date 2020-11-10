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

create sequence if not exists monitoring.accidents_id_seq;
create table if not exists monitoring.accidents
(
    id                                   integer not null default nextval('monitoring.accidents_id_seq'),
    foreign_id                           varchar   not null,
    name                                 varchar   not null,
    priority                             int,
    status_id                            int,
    short_description                    varchar,
    description                          varchar,
    impact_description                   varchar,
    failure_point                        varchar,
    detection_date                       timestamp not null,
    start_date                           timestamp,
    finish_date                          timestamp,
    predict_date                         timestamp,
    affected_systems                     varchar,
    localization_and_remediation_actions varchar,
    specialist_user_id                   int
);

ALTER SEQUENCE monitoring.accidents_id_seq
OWNED BY monitoring.accidents.id;
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

alter sequence monitoring.VtbIncidents_id_seq
owned by monitoring.VtbIncidents.id;

create sequence if not exists monitoring.VtbUnavailability_id_seq;
create table monitoring.VtbUnavailability
(
    id              integer not null default nextval('monitoring.VtbUnavailability_id_seq'),
    fault_id        varchar,
    begin_at        timestamp,
    end_at          timestamp,
    duration        interval,
    service_name    varchar,
    type            varchar,
    service_id      int,
    created_at      varchar,
    created_by_id   varchar,
    updated_at      timestamp,
    updated_by_id   varchar
);

alter sequence monitoring.VtbUnavailability_id_seq
owned by monitoring.VtbUnavailability.id;

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