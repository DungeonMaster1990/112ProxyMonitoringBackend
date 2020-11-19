create sequence if not exists monitoring.sm_rawdata_meas_id_seq;
create table if not exists monitoring.sm_rawdata_meas
(
    id                         integer not null default nextval('monitoring.sm_rawdata_meas_id_seq'),
    session_id                 int not null,
    measurement_id             int not null,
    sched_id                   int not null,
    category_id                int not null,
    monitor_id                 int,
    target_id                  int not null,
    msname                     varchar not null,
    msid                       int,
    userRemark                 varchar,
    connectionData             varchar,
    dmConnectionId             int,
    active                     int,
    ciId                       varchar,
    etiId                      varchar,
    integrationName            varchar,
    profileId                  varchar,
    modified_date              timestamp,
    creation_date              timestamp,
    is_deleted                 boolean
    );
alter sequence monitoring.sm_rawdata_meas_id_seq
owned by monitoring.sm_rawdata_meas.id;

create sequence if not exists monitoring.sm_def_measurements_id_seq;
create table if not exists monitoring.sm_def_measurements
(
    id                         integer not null default nextval('monitoring.sm_def_measurements_id_seq'),
    session_id                 int not null,
    measurement_id             int not null,
    sched_id                   int not null,
    category_id                int not null,
    monitor_id                 int not null,
    target_id                  int not null,
    msname                     varchar not null,
    Msid                       varchar,
    user_remark                varchar,
    connection_data            varchar,
    dm_connection_id           int ,
    is_active                  int not null,
    ci_id                      varchar,
    eti_id                     varchar,
    integration_name           varchar,
    profile_id                 varchar,
    modified_date              timestamp not null,
    creation_date              timestamp not null,
    raw_threshold_quality      int,
    is_deleted                 boolean,
    meas_value                 varchar
);
alter sequence monitoring.sm_def_measurements_id_seq
owned by monitoring.sm_def_measurements.id;

create sequence if not exists monitoring.metrics_id_seq;
create table if not exists monitoring.metrics
(
    id                         integer not null default nextval('monitoring.metrics_id_seq'),
    measurement_id             int not null,
    msname                     varchar not null,
    monitor_id                 int not null,
    is_merged                  boolean default false
);
alter sequence monitoring.metrics_id_seq
owned by monitoring.metrics.id;
