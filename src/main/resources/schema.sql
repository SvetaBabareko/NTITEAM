DROP TABLE IF EXISTS planets cascade;
DROP TABLE IF EXISTS lords;
DROP SEQUENCE  IF EXISTS global_seq;

create sequence global_seq start with 1;

create table lords
(
    id                      integer default global_seq.nextval      primary key,
    name                    varchar                                 not null,
    age                     integer                                 not null
);
create unique index users_unique_idx on lords (name);

create table planets
(
    id                      integer default global_seq.nextval      primary key,
    name                    varchar                                 not null,
    lord_id                 integer                                 DEFAULT NULL,
    foreign key (lord_id) references lords (id) on delete cascade
);