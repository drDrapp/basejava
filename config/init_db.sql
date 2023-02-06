drop table if exists contact;
drop table if exists resume;

create table resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text     not null
);

create table contact
(
    id          serial
        constraint contact_pk
            primary key,
    resume_uuid char(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on update restrict on delete cascade,
    type        text     not null,
    value       text     not null
);

create unique index contact_resume_uuid_type_index
    on contact (resume_uuid, type);

alter table if exists resume
    owner to postgres;

alter table contact
    owner to postgres;