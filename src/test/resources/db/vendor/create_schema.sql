CREATE schema bsm_replica;

create table bsm_replica.SM_DEF_MEASUREMENT
(
    session_id       int      not null,
    measurement_id   int      not null,
    sched_id         int      not null,
    category_id      int      not null,
    monitor_id       int,
    target_id        int      not null,
    msname           varchar  not null,
    msid             varchar,
    user_remark      varchar,
    connection_data  varchar,
    dm_connection_id int,
    active           int      not null,
    ci_id            char,
	eti_id char,
	integration_name varchar,
    profile_id varchar,
    modified_date timestamptz not null,
    creation_date timestamptz not null,
    is_deleted boolean not null
);

create table bsm_replica.SM_RAWDATA_MEAS
(
	session_id int not null,
	time_stamp timestamptz not null,
	measurement_id int not null,
	meas_value numeric not null,
	status_id int,
	err_msg varchar,
	raw_monitor_id int not null,
	raw_target_id int,
	raw_connection_id int not null,
	raw_category_id int not null,
	raw_threshold_quality int,
	dbdate timestamptz
);