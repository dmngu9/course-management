create table department (
    id                      serial primary key,
    name                    varchar(256) unique not null
);

create type EMPLOYMENT_TYPE as enum ('full-time', 'part-time', 'casual');
create type CONTRACT_STATUS as enum ('active', 'inactive');

create table instructor (
    id                      serial primary key,
    first_name              varchar(128) not null,
    last_name               varchar(128) not null,
    phone                   varchar(10) unique not null,
    email                   varchar(256) unique not null check (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    employment_type         EMPLOYMENT_TYPE not null,
    contract_status         CONTRACT_STATUS not null
);

create table course (
    id                          serial primary key,
    name                        varchar(256) unique not null,
    description                 varchar(256) not null,
    department_id               integer not null,
    instructor_id               integer not null,
    start_date                  date not null,
    end_date                    date not null check (end_date > start_date),
    constraint fk_department    foreign key (department_id) references department(id) on delete cascade,
    constraint fk_instructor    foreign key (instructor_id) references instructor(id) on delete cascade
);

create type STUDENT_TYPE as enum ('full-time', 'part-time');

create table student (
    id                          serial primary key,
    first_name                  varchar(128) not null,
    last_name                   varchar(128) not null,
    phone                       varchar(10) unique not null,
    email                       varchar(256) unique not null check (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    type                        STUDENT_TYPE not null
);

create table enrollment (
    course_id       integer not null,
    student_id      integer not null,
    constraint fk_course foreign key (course_id) references course(id) on delete cascade,
    constraint fk_student foreign key (student_id) references student(id) on delete cascade
);


