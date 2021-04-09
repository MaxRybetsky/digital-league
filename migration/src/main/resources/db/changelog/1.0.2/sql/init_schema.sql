create sequence if not exists usr_id_seq;
create sequence if not exists roles_id_seq;
create sequence if not exists permissions_id_seq;

create table if not exists usr
(
    id       bigint       not null unique default nextval('usr_id_seq'),
    login    varchar(30)  not null unique,
    password varchar(200) not null
);

create table if not exists roles
(
    id        bigint      not null unique default nextval('roles_id_seq'),
    role_name varchar(30) not null unique
);

create table if not exists permissions
(
    id         bigint      not null unique default nextval('permissions_id_seq'),
    permission varchar(30) not null unique
);

create table if not exists usr_roles
(
    usr_id  bigint,
    role_id bigint,
    constraint usr_roles_pk
        primary key (usr_id, role_id),
    constraint usr_fk
        foreign key (usr_id)
            references usr (id),
    constraint role_fk
        foreign key (role_id)
            references roles (id)
);

create table if not exists roles_permissions
(
    role_id bigint,
    permission_id  bigint,
    constraint roles_permissions_pk
        primary key (permission_id, role_id),
    constraint role_fk
        foreign key (role_id)
            references roles (id),
    constraint permission_pk
        foreign key (permission_id)
            references permissions (id)
);

alter sequence usr_id_seq
    owned by usr.id;
alter sequence roles_id_seq
    owned by roles.id;
alter sequence permissions_id_seq
    owned by permissions.id;