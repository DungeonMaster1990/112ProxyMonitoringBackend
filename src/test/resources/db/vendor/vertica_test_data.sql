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

insert into bsm_replica.SM_DEF_MEASUREMENT (session_id, measurement_id, sched_id, category_id, monitor_id, target_id,
                                            msname, msid, user_remark, connection_data, dm_connection_id, active, ci_id,
                                            eti_id, integration_name, profile_id, modified_date, creation_date,
                                            is_deleted)
values (115, 15100752, 1, 15100286, 15100261, 1510000003, 'Количество сессий vtb', null, '', null, 15100326, 1, '9',
        '1', null, 'ts_profile_1x', '2020-07-28 07:30:39', '2020-07-28 07:30:30', false);

insert into bsm_replica.SM_RAWDATA_MEAS
 SELECT 100, CURRENT_TIMESTAMP + 1, 15100752, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
 SELECT 101, CURRENT_TIMESTAMP + 1, 15100753, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
 SELECT 102, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 103, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 104, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 105, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 106, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 107, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 108, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 109, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 110, CURRENT_TIMESTAMP + 2, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 111, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP
UNION
  SELECT 112, CURRENT_TIMESTAMP + 1, 15100754, 32344.000, 0, null, 15100261, 151000003, 15100326, 15100286, 1, CURRENT_TIMESTAMP;