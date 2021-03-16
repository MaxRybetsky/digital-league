insert into locations(location_id, street_address, city)
values (1, 'Butyrskaya', 'Moscow'),
       (2, 'Tverskaya', 'Moscow'),
       (3, 'Wall St', 'New York'),
       (4, 'Baker St', 'London');

insert into jobs(jobs_id, job_title, min_salary, max_salary)
values ('ceo1', 'CEO', 30000, 50000),
       ('man1', 'Manager', 10000, 15000),
       ('hr1', 'HR', 1000, 5000),
       ('dev1', 'Programmer', 3000, 5000),
       ('dev2', 'Junior Programmer', 1000, 2000),
       ('devops1', 'DEV-OPS', 2000, 5000);

insert into employee(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id)
values (1, 'Peter', 'Parker', 'pparker@gmail.com', '79095438798', '2015-01-01', 'ceo1', 40000, 1),
       (2, 'Ivan', 'Petrov', 'petrov@gmail.com', '79095438438', '2015-03-01', 'man1', 11000, 1),
       (3, 'Mike', 'Smith', 'smith@gmail.com', '79665438798', '2017-07-07', 'hr1', 2000, 2),
       (4, 'John', 'Travolta', 'travol@gmail.com', '79067438798', '2016-04-04', 'dev1', 4000, 2),
       (5, 'Egor', 'Letov', 'el@go.com', '9888-12-23', '2016-05-01', 'devops1', 3000, 2);

insert into departments(department_id, department_name, manager_id, location_id)
values (1, 'Developers', 4, 1),
       (2, 'Top Management', 1, 3),
       (3, 'HR', 3, 4);

update employee
set department_id = 2
where employee_id = 1;

update employee
set department_id = 2
where employee_id = 2;

update employee
set department_id = 3
where employee_id = 3;

update employee
set department_id = 1
where employee_id = 4;

update employee
set department_id = 1
where employee_id = 5;

insert into job_history(employee_id, start_date, end_date, job_id, department_id)
values (1, '2015-01-01', '2015-12-01', 'hr1', 3),
       (4, '2016-04-04', '2016-12-04', 'devops1', 1),
       (4, '2016-12-04', '2017-04-04', 'dev2', 1);