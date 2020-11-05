
------------------ User ------------------
create sequence user_id_seq;
create table if not exists user (
    id      bigint          not null default nextval('user_id_seq'),
    name    varchar(100)    not null,

    constraint user_pk_id     primary key (id),
    constraint user_uk_name   unique (name)
);

------------------ Board Status ------------------
create table if not exists board_status (
    id      bigint          not null,
    name    varchar(100)    not null,

    constraint board_status_pk_id     primary key (id),
    constraint board_status_uk_name   unique (name)
);

------------------ Board ------------------
create sequence board_id_seq;
create table if not exists board (
    id              bigint      not null default nextval('board_id_seq'),
    user_id         bigint      not null,
    uuid            uuid        not null,
    status_id       bigint      not null,
    row_size        integer     not null,
    column_size     integer     not null,
    mines           integer     not null,
    created_at      timestamp   not null,
    started_at      timestamp,
    finished_at     timestamp,

    constraint board_pk_id     primary key (id),
    constraint board_uk_uuid unique (uuid),
    constraint board_fk_board_status foreign key (status_id) references board_status(id),
    constraint board_fk_user foreign key (user_id) references user(id)
);

------------------ Board Field Status ------------------
create table if not exists board_field_status (
    id      bigint          not null,
    name    varchar(100)    not null,

    constraint board_field_status_pk_id     primary key (id),
    constraint board_field_status_uk_name   unique (name)
);

------------------ Board Field ------------------
create sequence board_field_id_seq;
create table if not exists board_field (
    id              bigint      not null default nextval('board_field_id_seq'),
    board_id        bigint      not null,
    row_index       integer     not null,
    column_index    integer     not null,
    value           smallint    not null,
    status_id       bigint      not null,

    constraint board_field_pk_id primary key (id),
    constraint board_field_uk_board_id_row_column unique (board_id, row_index, column_index),
    constraint board_field_fk_board foreign key (board_id) references board(id),
    constraint board_field_fk_board_field_status foreign key (status_id) references board_field_status(id)
);

create index board_field_idx_board_id_status_id on board_field (board_id, status_id);