INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 1');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 2');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_3', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 3');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_4', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 4');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_5', '2020-11-16 16:00:00', 'Проблема', 'Иванов Василий 5');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_6', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 6');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_7', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 7');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_8', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 8');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_9', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 9');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_10', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 10');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_11', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 11');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_12', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 12');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Инцидент А_13', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 13');
INSERT INTO monitoring.incidents (incident_id, created_at, description, specialist_id) VALUES ('Проблема А_14', '2120-11-17 19:00:00', 'Самая свежая проблема', 'Иванов Василий 14');

insert into monitoring.systems (name, accidentid) values ('Переводы', 1);
insert into monitoring.systems (name, accidentid) values ('Переводы', 2);
insert into monitoring.systems (name, accidentid) values ('Переводы', 3);
insert into monitoring.systems (name, accidentid) values ('Переводы', 4);
insert into monitoring.systems (name, accidentid) values ('Переводы', 5);
insert into monitoring.systems (name, accidentid) values ('Переводы', 6);
insert into monitoring.systems (name, accidentid) values ('Переводы', 7);
insert into monitoring.systems (name, accidentid) values ('Переводы', 8);
insert into monitoring.systems (name, accidentid) values ('Переводы', 9);
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

insert into monitoring.sm_rawdata_meas (id, session_id, time_stamp, measurement_id, meas_value, raw_monitor_id, raw_target_id, raw_connection_id, raw_category_id, raw_threshold_quality) values
    -- 'Клиентов в ВТБ Онлайн'
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp  - INTERVAL '3 DAY', 1, 30, 0, 0, 0, 0, 0),
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp  - INTERVAL '2 DAY', 1, 20, 0, 0, 0, 0, 0),
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp  - INTERVAL '1 DAY', 1, 10, 0, 0, 0, 0, 0),
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp, 1, 77, 0, 0, 0, 0, 0),
    -- 'Процент доставленных PUSH'
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp  - INTERVAL '1 DAY', 6, 11, 0, 0, 0, 0, 0),
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp  - INTERVAL '1 HOUR', 6, 100, 0, 0, 0, 0, 0),
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp, 6, 77, 0, 0, 0, 0, 0),
    --'Новых переводов между своими счетами'
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp  - INTERVAL '3 DAY', 4, 33, 0, 0, 0, 0, 0),
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp  - INTERVAL '2 DAY', 4, 22, 0, 0, 0, 0, 0),
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp  - INTERVAL '1 DAY', 4, 11, 0, 0, 0, 0, 0),
    (nextval('monitoring.sm_rawdata_meas_id_seq'), 0, current_timestamp, 4, 77, 0, 0, 0, 0, 0)


