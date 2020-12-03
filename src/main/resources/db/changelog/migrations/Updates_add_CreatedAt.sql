alter table monitoring.updates
  add if not exists start_time timestamp default now();