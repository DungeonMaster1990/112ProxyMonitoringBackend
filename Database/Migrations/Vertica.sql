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
    time_stamp                 timestamp not null,
    measurement_id             int not null,
    meas_value                 numeric,
    status_id                  int,
    errMsg                     varchar,
    raw_monitor_id             int not null,
    raw_target_id              int not null,
    raw_connection_id          int not null,
    raw_category_id            int not null,
    raw_threshold_quality      int,
    dbdate                     timestamp
);
alter sequence monitoring.sm_def_measurements_id_seq
owned by monitoring.sm_def_measurements.id;

create sequence if not exists monitoring.metrics_id_seq;
create table if not exists monitoring.metrics
(
    id                         integer not null default nextval('monitoring.metrics_id_seq'),
    measurement_id             int not null,
    msname                     varchar not null
);
alter sequence monitoring.metrics_id_seq
owned by monitoring.metrics.id;
