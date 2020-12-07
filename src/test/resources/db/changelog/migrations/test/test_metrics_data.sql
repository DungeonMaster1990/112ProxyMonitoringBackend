--@formatter:off
insert into monitoring.metrics (id, measurement_id, monitor_id, msname)
values (nextval('monitoring.metrics_id_seq'), 1, currval('monitoring.metrics_id_seq'), 'Клиентов в ВТБ Онлайн'),
       (nextval('monitoring.metrics_id_seq'), 2, currval('monitoring.metrics_id_seq'), 'Очередь на исполнение документов БО'),
       (nextval('monitoring.metrics_id_seq'), 3, currval('monitoring.metrics_id_seq'), 'Количество новых операций в ВТБ-онлайн'),
       (nextval('monitoring.metrics_id_seq'), 4, currval('monitoring.metrics_id_seq'), 'Новых переводов между своими счетами'),
       (nextval('monitoring.metrics_id_seq'), 5, currval('monitoring.metrics_id_seq'), 'Время формирования выписки'),
       (nextval('monitoring.metrics_id_seq'), 6, currval('monitoring.metrics_id_seq'), 'Процент доставленных PUSH'),
       (nextval('monitoring.metrics_id_seq'), 7, currval('monitoring.metrics_id_seq'), 'Процент доставленных SMS');

with meas as (
    select m.measurement_id id
    from monitoring.metrics m
    where m.msname = 'Выданные КК'
),
     dt(ts, val) as (
         values (current_timestamp - INTERVAL '3 DAY', 30),
                (current_timestamp - INTERVAL '2 DAY', 20),
                (current_timestamp - INTERVAL '1 DAY', 10),
                (current_timestamp, 1000000)
     )
insert
into monitoring.sm_rawdata_meas(id, session_id, time_stamp, measurement_id, meas_value, raw_monitor_id, raw_target_id, raw_connection_id, raw_category_id, raw_threshold_quality)
select nextval('monitoring.sm_rawdata_meas_id_seq'),0, dt.ts, meas.id, dt.val, 0, 0, 0, 0, 0
from meas, dt;


with meas as (
    select m.measurement_id id
    from monitoring.metrics m
    where m.msname = 'Количество сессий ДБО'
),
     dt(ts, val) as (
         values (current_timestamp - INTERVAL '1 DAY', 11),
                (current_timestamp - INTERVAL '1 HOUR', 100),
                (current_timestamp, 1000000)
     )
insert
into monitoring.sm_rawdata_meas(id, session_id, time_stamp, measurement_id, meas_value, raw_monitor_id, raw_target_id, raw_connection_id, raw_category_id, raw_threshold_quality)
select nextval('monitoring.sm_rawdata_meas_id_seq'), 0, dt.ts, meas.id, dt.val, 0, 0, 0, 0, 0
from meas,dt;

with meas as (
    select m.measurement_id id
    from monitoring.metrics m
    where m.msname = 'Новых заявок на ДКО'
),
     dt(ts, val) as (
         values (current_timestamp - INTERVAL '3 DAY', 33),
                (current_timestamp - INTERVAL '2 DAY', 22),
                (current_timestamp - INTERVAL '1 DAY', 11),
                (current_timestamp, 1000000)
     )
insert
into monitoring.sm_rawdata_meas(id, session_id, time_stamp, measurement_id, meas_value, raw_monitor_id, raw_target_id, raw_connection_id, raw_category_id, raw_threshold_quality)
select nextval('monitoring.sm_rawdata_meas_id_seq'), 0, dt.ts, meas.id, dt.val, 0, 0, 0, 0, 0
from meas, dt;
--@formatter:on
