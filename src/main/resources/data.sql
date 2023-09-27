create table discussione (id serial not null, id_utente integer not null, nome varchar(255), primary key (id));
create table messaggio (id serial not null, id_discussione integer not null, id_utente integer not null, testo varchar(255), primary key (id));
create table utente (id serial not null, cognome varchar(255), nome varchar(255), primary key (id));
alter table if exists discussione add constraint FKfmu0hh7msok0r6u2qj3js8dkj foreign key (id_utente) references utente;
alter table if exists messaggio add constraint FKah0wytki8c5jddv30woqi0x1k foreign key (id_discussione) references discussione;
alter table if exists messaggio add constraint FKbtfhsx82u6921aeacdhhhr5qm foreign key (id_utente) references utente;
