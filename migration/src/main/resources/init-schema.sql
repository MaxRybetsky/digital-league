create table locations
(
    location_id    serial primary key,
    street_address varchar(40),
    city           varchar(30)
);

create table jobs
(
    jobs_id    varchar(10) not null primary key,
    job_title  varchar(35),
    min_salary int CHECK ( min_salary > 0 ),
    max_salary int CHECK ( max_salary > min_salary )
);

create table departments
(
    department_id   serial primary key,
    department_name varchar(30),
    manager_id      int unique,
    location_id     int,
    constraint location_id
        foreign key (location_id)
            references locations (location_id)
);

create table job_history
(
    employee_id   int,
    start_date    date,
    end_date      date CHECK ( end_date > start_date ),
    job_id        varchar(10) unique,
    department_id int,
    constraint job_id
        foreign key (job_id)
            references jobs (jobs_id)
);

create table employee
(
    employee_id   serial primary key,
    first_name    varchar(20),
    last_name     varchar(25),
    email         varchar(25) unique,
    phone_number  varchar(20),
    hire_date     date,
    job_id        varchar(10),
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
            references employee (employee_id);

alter table job_history
    add primary key (employee_id, start_date),
    add constraint employee_id
        foreign key (employee_id)
            references employee (employee_id),
    add constraint jobs_id
        foreign key (job_id)
            references jobs (jobs_id),
    add constraint department_id
        foreign key (department_id)
            references departments (department_id);