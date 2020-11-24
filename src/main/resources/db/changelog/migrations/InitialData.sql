do $$
    declare p_update_time timestamp = now();
begin

insert into monitoring.updates ( service_name, update_time)
values ('Incidents', p_update_time),
       ('Unavalabilities', p_update_time),
       ('Changes', p_update_time),
       ('VerticaSmRawData', p_update_time);
end $$


do $$
begin

insert into monitoring.metrics (measurement_id, msname, monitor_id, is_merged)
values (12640807, 'Количество переводов ТБ через МБ', 5343999, false),
       (15100752, 'Количество сессий ДБО', 15100261, false),
       (6098425, 'Количество входов в МБ', 5343999, false),
       (9105257, 'Количество активных операций в ВТБ – Онлайн', 9100354, false),
       (6640147, 'Выданные КК', 6504482, false),
       (11259937, 'Выданные КН', 5168177, false),
       (11052715, 'Новых заявок на ДКО', 5168177, false),
       (7200082, 'Новых кредитных заявок в FICO', 7108091, false),
       (12161243, 'Количество операций по картам Профайл', 12127204, false),
       (12161243, 'Количество операций по картам Way4 экс ВТБ24', 12127204, false),
       (6917296, 'Исполненные платежи в бизнес-онлайн', 6909311, false);
end $$