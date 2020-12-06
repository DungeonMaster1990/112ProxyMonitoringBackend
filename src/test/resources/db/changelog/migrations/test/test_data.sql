INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id) VALUES ('Инцидент А_1', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 1');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id) VALUES ('Инцидент А_2','1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 2');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id) VALUES ('Инцидент А_3','1','2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 3');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id) VALUES ('Инцидент А_4','1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 4');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id) VALUES ('Инцидент А_5','3', '2020-11-16 16:00:00', 'Проблема', 'Иванов Василий 5');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Инцидент А_6', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 6');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Инцидент А_7', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 7');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Инцидент А_8', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 8');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Инцидент А_9', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 9');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Инцидент А_10', '3', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 10');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Инцидент А_11', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 11');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Инцидент А_12', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 12');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Инцидент А_13', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 13');
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, specialist_id)
VALUES ('Проблема А_14', '1', '2120-11-17 19:00:00', 'Самая свежая проблема', 'Иванов Василий 14');

UPDATE monitoring.incidents
SET resolution            = 'УНК в ВТБ-Онлайн: 10632683' ||
                            '#https://bankvtb.webex.com/meet/xxx#',
    configuration_item_id = 'М-Банк'
WHERE id = 5;

insert into monitoring.systems (name, accidentid)
values ('Переводы', 1);
insert into monitoring.systems (name, accidentid)
values ('Переводы', 2);
insert into monitoring.systems (name, accidentid)
values ('Переводы', 3);
insert into monitoring.systems (name, accidentid)
values ('Переводы', 4);
insert into monitoring.systems (name, accidentid)
values ('Переводы', 5);
insert into monitoring.systems (name, accidentid)
values ('Переводы', 6);
insert into monitoring.systems (name, accidentid)
values ('Переводы', 7);
insert into monitoring.systems (name, accidentid)
values ('Переводы', 8);
insert into monitoring.systems (name, accidentid)
values ('Переводы', 9);
insert into monitoring.systems (name, accidentid) values ('Переводы', 10);
insert into monitoring.systems (name, accidentid) values ('Переводы', 11);
insert into monitoring.systems (name, accidentid) values ('Переводы', 12);
insert into monitoring.systems (name, accidentid) values ('Переводы', 13);

insert into monitoring.systems (name, accidentid) values ('Платежи', 1);
insert into monitoring.systems (name, accidentid) values ('Платежи', 2);
insert into monitoring.systems (name, accidentid) values ('Платежи', 3);
insert into monitoring.systems (name, accidentid) values ('Платежи', 4);
insert into monitoring.systems (name, accidentid) values ('Платежи', 5);
insert into monitoring.systems (name, accidentid) values ('Платежи', 6);
insert into monitoring.systems (name, accidentid) values ('Платежи', 7);
insert into monitoring.systems (name, accidentid) values ('Платежи', 8);
insert into monitoring.systems (name, accidentid) values ('Платежи', 9);
insert into monitoring.systems (name, accidentid) values ('Платежи', 10);
insert into monitoring.systems (name, accidentid) values ('Платежи', 11);
insert into monitoring.systems (name, accidentid) values ('Платежи', 12);
insert into monitoring.systems (name, accidentid) values ('Платежи', 13);

insert into monitoring.metrics (id, measurement_id, monitor_id, msname) values
    (nextval ('monitoring.metrics_id_seq'), 1, currval('monitoring.metrics_id_seq'), 'Клиентов в ВТБ Онлайн'),
    (nextval ('monitoring.metrics_id_seq'), 2, currval('monitoring.metrics_id_seq'), 'Очередь на исполнение документов БО'),
    (nextval ('monitoring.metrics_id_seq'), 3, currval('monitoring.metrics_id_seq'), 'Количество новых операций в ВТБ-онлайн'),
    (nextval ('monitoring.metrics_id_seq'), 4, currval('monitoring.metrics_id_seq'), 'Новых переводов между своими счетами'),
    (nextval ('monitoring.metrics_id_seq'), 5, currval('monitoring.metrics_id_seq'), 'Время формирования выписки'),
    (nextval ('monitoring.metrics_id_seq'), 6, currval('monitoring.metrics_id_seq'), 'Процент доставленных PUSH'),
    (nextval ('monitoring.metrics_id_seq'), 7, currval('monitoring.metrics_id_seq'), 'Процент доставленных SMS');

with meas as (
    select m.measurement_id id
      from monitoring.metrics m
     where m.msname  = 'Выданные КК'
), dt(ts, val) as (
      values
    (current_timestamp  - INTERVAL '3 DAY', 30),
    (current_timestamp  - INTERVAL '2 DAY', 20),
    (current_timestamp  - INTERVAL '1 DAY', 10),
    (current_timestamp,                     1000000)
)
insert into monitoring.sm_rawdata_meas(id, session_id, time_stamp, measurement_id, meas_value, raw_monitor_id, raw_target_id, raw_connection_id, raw_category_id, raw_threshold_quality)
select nextval('monitoring.sm_rawdata_meas_id_seq'), 0, dt.ts, meas.id, dt.val, 0, 0, 0, 0, 0 from meas, dt;


with meas as (
    select m.measurement_id id
      from monitoring.metrics m
     where m.msname  = 'Количество сессий ДБО'
), dt(ts, val) as (
      values
    (current_timestamp  - INTERVAL '1 DAY',  11),
    (current_timestamp  - INTERVAL '1 HOUR', 100),
    (current_timestamp,                     1000000)
)
insert into monitoring.sm_rawdata_meas(id, session_id, time_stamp, measurement_id, meas_value, raw_monitor_id, raw_target_id, raw_connection_id, raw_category_id, raw_threshold_quality)
select nextval('monitoring.sm_rawdata_meas_id_seq'), 0, dt.ts, meas.id, dt.val, 0, 0, 0, 0, 0 from meas, dt;

with meas as (
    select m.measurement_id id
      from monitoring.metrics m
     where m.msname  = 'Новых заявок на ДКО'
), dt(ts, val) as (
      values
          (current_timestamp  - INTERVAL '3 DAY',  33),
          (current_timestamp - INTERVAL '2 DAY', 22),
          (current_timestamp - INTERVAL '1 DAY', 11),
          (current_timestamp, 1000000)
)
insert
into monitoring.sm_rawdata_meas(id, session_id, time_stamp, measurement_id, meas_value, raw_monitor_id, raw_target_id,
                                raw_connection_id, raw_category_id, raw_threshold_quality)
select nextval('monitoring.sm_rawdata_meas_id_seq'),
       0,
       dt.ts,
       meas.id,
       dt.val,
       0,
       0,
       0,
       0,
       0
from meas,
     dt;

INSERT INTO monitoring.changes (change_id, status, description, vtb_risk_description, initial_impact, planned_start_at,
                                planned_end_at, down_start_at, down_end_at, requested_by, requested_for,
                                affected_services)
VALUES ('Изменение IM-1', 'Согласование', 'Описание', 'Описание последствия', 'Не влияет', '2020-11-16 16:00:00',
        '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '', '', 'Платежи,Переводы'),
       ('Изменение IM-2', 'Согласование', 'Подробное описание', 'Описание последствия', 'Не влияет',
        '2020-11-16 16:00:00', '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '', '',
        'Платежи,Переводы'),
       ('Изменение IM-3', 'Согласование', 'Описание', 'Описание последствия', 'Не влияет', '2020-11-16 16:00:00',
        '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', 'Иванов Василий', 'Петров Иван',
        'Платежи,Переводы');

INSERT INTO monitoring.changes (change_id, planned_start_at, planned_end_at, category, affected_services, current_phase)
values ('Изменение IM-1', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Экстренное', 'Платежи,Переводы',
        'Регистрация'),
       ('Изменение IM-2', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-3', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Экстренное', 'Платежи,Переводы',
        'Анализ результатов реализации'),
       ('Авария IM-3', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-4', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Экстренное', 'Платежи,Переводы',
        'Авторизация'),
       ('Изменение IM-5', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-6', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Стандартное', 'Платежи,Переводы',
        'Реализация'),
       ('Изменение IM-7', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Стандартное', 'Платежи,Переводы', 'Закрытие'),
       ('Изменение IM-8', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Нормальное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-10', '2020-11-10 10:00:00', '2020-11-20 20:00:00', 'Нормальное', 'Платежи,Переводы',
        'Реализация');