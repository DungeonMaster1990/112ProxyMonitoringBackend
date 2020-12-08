alter table monitoring.changes
    rename column vtb_risk_description to risk_description;

update monitoring.updates
    set update_time = update_time - INTERVAL '7 DAY'
where service_name = 'Changes';

drop table if exists accidentstatuses;
drop table if exists failurestatuses;
drop table if exists plantypes;
drop table if exists systems;