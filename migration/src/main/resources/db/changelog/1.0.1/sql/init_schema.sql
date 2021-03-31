create table if not exists  locations
(
    location_id    serial primary key,
    street_address varchar(40),
    city           varchar(30) not null
);

create table if not exists  jobs
(
    jobs_id    varchar(10) not null primary key,
    job_title  varchar(35) not null,
    min_salary int CHECK ( min_salary > 0 ),
    max_salary int CHECK ( max_salary > min_salary )
);

create table if not exists departments
(
    department_id   serial primary key,
    department_name varchar(30) not null,
    manager_id      int unique,
    location_id     int,
    constraint location_id
        foreign key (location_id)
            references locations (location_id)
            on delete set null
);

create table if not exists  job_history
(
    employee_id   int                                  not null,
    start_date    date                                 not null,
    end_date      date CHECK ( end_date > start_date ) not null,
    job_id        varchar(10)                          not null,
    department_id int,
    constraint job_id
        foreign key (job_id)
            references jobs (jobs_id)
            on delete set null
);

create table if not exists  employee
(
    employee_id   serial primary key,
    first_name    varchar(20),
    last_name     varchar(25)        not null,
    email         varchar(25) unique not null,
    phone_number  varchar(20),
    hire_date     date               not null,
    job_id        varchar(10)        not null,
    salary        int CHECK ( salary > 0 ),
    manager_id    int,
    department_id int,
    constraint job_id
        foreign key (job_id)
            references jobs (jobs_id),
    constraint manager_id
        foreign key (manager_id)
            references employee (employee_id),
    constraint department_id
        foreign key (department_id)
            references departments (department_id)
);

alter table departments
    add constraint manager_id
        foreign key (manager_id)
            references employee (employee_id)
            on delete set null;

alter table job_history
    add primary key (employee_id, start_date),
    add constraint employee_id
        foreign key (employee_id)
            references employee (employee_id)
            on delete cascade,
    add constraint jobs_id
        foreign key (job_id)
            references jobs (jobs_id),
    add constraint department_id
        foreign key (department_id)
            references departments (department_id);