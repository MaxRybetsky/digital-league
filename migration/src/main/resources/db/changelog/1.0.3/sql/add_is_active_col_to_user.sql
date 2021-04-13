create sequence if not exists status_id_seq;

create table if not exists status
(
    id          bigint      not null unique default nextval('status_id_seq'),
    status_name varchar(30) not null unique
);

alter table usr
    add column status bigint;

alter table usr
    add constraint usr_status_fk
        foreign key (status)
            references status (id);