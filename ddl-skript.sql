drop schema if exists dat108oblig3 cascade;
create schema dat108oblig3;
SET search_path = dat108oblig3;

drop table if exists deltagere;

create table deltagere
(
	mobil varchar(8) primary key,
	fornavn varchar(30) not null,
	etternavn varchar(30) not null,
	passordHash varchar(300) not null,
	kjonn varchar(10) not null,
	passordSalt varchar(100) not null
);