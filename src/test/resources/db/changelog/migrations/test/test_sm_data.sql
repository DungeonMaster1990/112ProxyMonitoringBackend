--@formatter:off
INSERT INTO monitoring.incidents (incident_id, priority, created_at, description, manager_id, idented_at)
VALUES ('Инцидент А_1', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 1', '2020-11-16 19:00:00'),
       ('Инцидент А_2', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 2', '2020-11-16 19:00:00'),
       ('Инцидент А_3', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 3', '2020-11-16 19:00:00'),
       ('Инцидент А_4', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 4', '2020-11-16 19:00:00'),
       ('Инцидент А_5', '3', '2020-11-16 16:00:00', 'Проблема', 'Иванов Василий 5', '2020-11-16 16:00:00'),
       ('Инцидент А_6', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 6', '2020-11-16 19:00:00'),
       ('Инцидент А_7', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 7', '2020-11-16 19:00:00'),
       ('Инцидент А_8', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 8', '2020-11-16 19:00:00'),
       ('Инцидент А_9', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 9', '2020-11-16 19:00:00'),
       ('Инцидент А_10', '3', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 10', '2020-11-16 19:00:00'),
       ('Инцидент А_11', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 11', '2020-11-16 19:00:00'),
       ('Инцидент А_12', '1', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 12', '2020-11-16 19:00:00'),
       ('Инцидент А_13', '2', '2020-11-16 19:00:00', 'Проблема', 'Иванов Василий 13', '2020-11-16 19:00:00'),
       ('Проблема А_14', '1', '2120-11-17 19:00:00', 'Самая свежая проблема', 'Иванов Василий 14', '2020-11-17 19:00:00');

UPDATE monitoring.incidents
SET resolution            = 'УНК в ВТБ-Онлайн: 10632683#' ||
                            '#https://bankvtb.webex.com/meet/xxx#',
    configuration_item_id = 'М-Банк'
WHERE id = 5;

insert into monitoring.unavailability (service_name, fault_id)
values ('Переводы', 'Инцидент А_1'),
       ('Переводы', 'Инцидент А_2'),
       ('Переводы', 'Инцидент А_3'),
       ('Переводы', 'Инцидент А_4'),
       ('Переводы', 'Инцидент А_5'),
       ('Переводы', 'Инцидент А_6'),
       ('Переводы', 'Инцидент А_7'),
       ('Переводы', 'Инцидент А_8'),
       ('Переводы', 'Инцидент А_9'),
       ('Переводы', 'Инцидент А_10'),
       ('Переводы', 'Инцидент А_11'),
       ('Переводы', 'Инцидент А_12'),
       ('Переводы', 'Инцидент А_13'),
       ('Платежи', 'Инцидент А_1'),
       ('Платежи', 'Инцидент А_2'),
       ('Платежи', 'Инцидент А_3'),
       ('Платежи', 'Инцидент А_4'),
       ('Платежи', 'Инцидент А_5'),
       ('Платежи', 'Инцидент А_6'),
       ('Платежи', 'Инцидент А_7'),
       ('Платежи', 'Инцидент А_8'),
       ('Платежи', 'Инцидент А_9'),
       ('Платежи', 'Инцидент А_10'),
       ('Платежи', 'Инцидент А_11'),
       ('Платежи', 'Инцидент А_12'),
       ('Платежи', 'Инцидент А_13');

INSERT INTO monitoring.changes (change_id, status, current_phase, brief_description, description, risk_description, initial_impact, planned_start_at,
                                planned_end_at, sched_outage_start_at, sched_outage_end_at, requested_by, requested_for,
                                affected_services)
VALUES ('Изменение IM-1', 'На согласовании', 'Согласование', 'Краткое описание1', 'Подробное описание1', 'Описание последствия1', 'Не влияет', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '', '', 'Платежи,Переводы'),
       ('Изменение IM-2', 'На согласовании', 'Согласование', 'Краткое описание2', 'Подробное описание2', 'Описание последствия2', 'Не влияет', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '', '', 'Платежи,Переводы'),
       ('Изменение IM-3', 'На согласовании', 'Согласование', 'Краткое описание3', 'Подробное описание3', 'Описание последствия3', 'Не влияет', '2020-11-16 16:00:00', '2020-11-16 19:00:00', '2020-11-16 16:00:00', '2020-11-16 19:00:00', 'Иванов Василий', 'Петров Иван', 'Платежи,Переводы');

INSERT INTO monitoring.changes (change_id, planned_start_at, planned_end_at, category, affected_services, current_phase)
values ('Изменение IM-1',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-2',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Закрытие'),
       ('Изменение IM-3',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Анализ результатов реализации'),
       ('Авария IM-3',      current_timestamp - INTERVAL '4 DAY',  current_timestamp + INTERVAL '4 DAY',  'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-4',   current_timestamp + INTERVAL '1 HOUR', current_timestamp + INTERVAL '2 HOUR', 'Экстренное', 'Платежи,Переводы', 'Анализ результатов реализации'),
       ('Изменение IM-5',   current_timestamp - INTERVAL '1 HOUR', current_timestamp + INTERVAL '1 HOUR', 'Экстренное', 'Платежи,Переводы', 'Реализация'),
       ('Изменение IM-6',   current_timestamp - INTERVAL '2 DAY',  current_timestamp + INTERVAL '2 DAY',  'Стандартное', 'Платежи,Переводы', 'Авторизация'),
       ('Изменение IM-7',   current_timestamp - INTERVAL '2 DAY',  current_timestamp + INTERVAL '2 DAY',  'Стандартное', 'Платежи,Переводы', 'Согласование'),
       ('Изменение IM-8',   current_timestamp - INTERVAL '2 DAY' - INTERVAL '1 hour',  current_timestamp + INTERVAL '2 DAY',  'Нормальное', 'Платежи,Переводы', 'Планирование'),
       ('Изменение IM-9',   current_timestamp - INTERVAL '2 DAY' - INTERVAL '2 hour',  current_timestamp + INTERVAL '2 DAY',  'Нормальное', 'Платежи,Переводы', 'Согласование'),
       ('Изменение IM-10',  current_timestamp - INTERVAL '2 DAY' - INTERVAL '3 hour',  current_timestamp + INTERVAL '2 DAY',  'Нормальное', 'Платежи,Переводы', 'Авторизация'),
       ('Изменение IM-11',  current_timestamp - INTERVAL '2 DAY' - INTERVAL '4 hour',  current_timestamp + INTERVAL '2 DAY',  'Нормальное', 'Платежи,Переводы', 'Анализ результатов реализации');
--@formatter:on
