alter table monitoring.incidents
  add if not exists notification_sent boolean default false;