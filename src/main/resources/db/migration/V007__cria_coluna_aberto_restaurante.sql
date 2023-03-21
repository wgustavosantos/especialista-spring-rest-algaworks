alter table restaurante add column aberto boolean not null;
update restaurante set restaurante.aberto = false;