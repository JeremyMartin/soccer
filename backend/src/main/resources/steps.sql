insert into step (id, name_en, name_fr) select 1, 'Pool', 'Pool' where not exists (select 1 from step where id=1);
insert into step (id, name_en, name_fr) select 2, 'First phase', 'Première phase' where not exists (select 1 from step where id=2);
insert into step (id, name_en, name_fr) select 3, 'Final phase', 'Phase finale' where not exists (select 1 from step where id=3);
