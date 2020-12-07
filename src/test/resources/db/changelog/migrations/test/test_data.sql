INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_1', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 1', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_2', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 2', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_3', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 3', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_4', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 4', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_5', '3', '2020-11-16 16:00:00', 'Проблема', 'Иванов Василий 5', '2020-11-16 16:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_6', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 6', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_7', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 7', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_8', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 8', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_9', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 9', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_10', '3', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 10', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_11', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 11', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_12', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 12', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Инцидент А_13', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 13', '2020-11-16 19:00:00');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id, idented_at) VALUES ('Проблема А_14', '1', '2120-11-17 19:00:00', 'Самая свежая проблема', 'Иванов Василий 14', '2020-11-17 19:00:00');

UPDATE monitoring.incidents
SET resolution            = 'УНК в ВТБ-Онлайн: 10632683#' ||
                            '#https://bankvtb.webex.com/meet/xxx#',
    configuration_item_id = 'М-Банк'
WHERE id = 5;

insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_1');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_2');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_3');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_4');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_5');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_6');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_7');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_8');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_9');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_10');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_11');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_12');
insert into monitoring.unavailability (service_name, fault_id) values ('Переводы', 'Инцидент А_13');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_1');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_2');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_3');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_4');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_5');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_6');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_7');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_8');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_9');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_10');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_11');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_12');
insert into monitoring.unavailability (service_name, fault_id) values ('Платежи', 'Инцидент А_13');

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

INSERT INTO monitoring.changes (change_id, status, description, vtb_risk_description, initial_impact, planned_start_at,
                                planned_end_at, sched_outage_start_at, sched_outage_end_at, requested_by, requested_for,
                                affected_services)
VALUES ('Изменение IM-1', 'Согласование', 'Описание', 'Описание последствия', 'Не влияет', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '', '', 'Платежи,Переводы'),
       ('Изменение IM-2', 'Согласование', 'Подробное описание', 'Описание последствия', 'Не влияет', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '', '', 'Платежи,Переводы'),
       ('Изменение IM-3', 'Согласование', 'Описание', 'Описание последствия', 'Не влияет', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', 'Иванов Василий', 'Петров Иван', 'Платежи,Переводы');

INSERT INTO monitoring.changes (change_id, planned_start_at, planned_end_at, category, affected_services, current_phase)
values ('Изменение IM-1',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-2',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Закрытие'),
       ('Изменение IM-3',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Анализ результатов реализации'),
       ('Авария IM-3',      current_timestamp - INTERVAL '4 DAY',  current_timestamp + INTERVAL '4 DAY',  'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-4',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Анализ результатов реализации'),
       ('Изменение IM-5',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-6',   current_timestamp - INTERVAL '2 DAY',  current_timestamp + INTERVAL '2 DAY',  'Стандартное', 'Платежи,Переводы', 'Авторизация'),
       ('Изменение IM-7',   current_timestamp - INTERVAL '2 DAY',  current_timestamp + INTERVAL '2 DAY',  'Стандартное', 'Платежи,Переводы', 'Согласование'),
       ('Изменение IM-8',   current_timestamp - INTERVAL '2 DAY' - INTERVAL '1 hour',  current_timestamp + INTERVAL '2 DAY',  'Нормальное', 'Платежи,Переводы', 'Планирование'),
       ('Изменение IM-9',   current_timestamp - INTERVAL '2 DAY' - INTERVAL '2 hour',  current_timestamp + INTERVAL '2 DAY',  'Нормальное', 'Платежи,Переводы', 'Согласование'),
       ('Изменение IM-10',  current_timestamp - INTERVAL '2 DAY' - INTERVAL '3 hour',  current_timestamp + INTERVAL '2 DAY',  'Нормальное', 'Платежи,Переводы', 'Авторизация'),
       ('Изменение IM-11',  current_timestamp - INTERVAL '2 DAY' - INTERVAL '4 hour',  current_timestamp + INTERVAL '2 DAY',  'Нормальное', 'Платежи,Переводы', 'Анализ результатов реализации');