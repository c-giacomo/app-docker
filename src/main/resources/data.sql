create table discussione (id serial not null, nome varchar(255), primary key (id));
create table discussione_utente (discussione_id integer not null, utente_id integer not null);
create table messaggio (id serial not null, id_discussione integer not null, id_utente integer not null, testo varchar(255), primary key (id));
create table utente (id serial not null, cognome varchar(255), nome varchar(255), primary key (id));
alter table if exists discussione_utente add constraint discussioneutente_utenteid foreign key (utente_id) references utente;
alter table if exists discussione_utente add constraint discussioneutente_discussioneid foreign key (discussione_id) references discussione;
alter table if exists messaggio add constraint messaggio_iddiscussione foreign key (id_discussione) references discussione;
alter table if exists messaggio add constraint messaggio_idutente foreign key (id_utente) references utente;
