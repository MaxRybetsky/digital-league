-- #1
-- Запрос, выводящий количество повторяющихся строк.
-- Подсчитывает количество разработчиков категории 'dev1'.
select count(*)
from employee
where job_id = 'dev1';

-- #2
-- Запрос, выводящий максимальную зарплату в каждом отделе.
select d.department_name, max(e.salary)
from departments d
         inner join employee e on d.department_id = e.department_id
group by d.department_name;

-- #3
-- Запрос, выводящий общие записи из 2 таблиц.
select *
from employee
         inner join jobs j on j.jobs_id = employee.job_id;

-- #4
-- Запрос, удаляющий повторяющиеся строки в таблице.
-- Данный запрос оставляет в компании только одного представителя определенной
-- професии. После его выполнения останется один разработчик, один менеджер и т.д.
delete
from employee e1
    using employee e2
where e1.ctid < e2.ctid
  and e1.job_id = e2.job_id;

-- #5
-- Запрос, выводящий первые 50% записей из таблицы.
select *
from employee
order by employee_id
limit (select count(*) from employee) / 2;

-- #6
-- Запрос, выводящий список сотрудников, которые не прикреплены ни
-- к какому отделу.
select *
from employee
where department_id is null;

-- #7
-- Представление из нескольких таблиц.
create view employees_base_info
as
select e.employee_id                                as id,
       e.first_name                                 as name,
       e.last_name                                  as last_name,
       coalesce(d.department_name, 'No department') as department,
       j.job_title                                  as job
from employee e
         left join jobs j on j.jobs_id = e.job_id
         left join departments d on d.department_id = e.department_id
order by e.employee_id;

-- #8
-- Хранимая процедура для извлечения данных из таблицы.
-- Выводит основную информацию (id, имя, фамилия, почта,
-- телефон) о работниках.
create or replace procedure print_employees_base_info()
    language plpgsql
as
$$
declare
    rec record;
begin
    raise notice 'Base Employees Information';
    for rec in (select * from employee order by employee_id)
        loop
            raise notice 'ID: %, NAME: %, LAST NAME: %, EMAIL: %, PHONE: %',
                rec.employee_id,
                rec.first_name,
                rec.last_name,
                rec.email,
                rec.phone_number;
        end loop;
end;
$$;

-- #9
-- Хранимая процедура, которая модифицирует таблицу:
-- Устанавливает новую зарплату для сотрудника с данным id.
-- Выбрасывает исключения с описанием ошибки в случае ее обнаружения.
create or replace procedure set_new_salary(id int, new_salary int)
    language plpgsql
as
$$
declare
    row record;
begin
    raise notice 'Start process of setting new salary % for employee with id=%',
        new_salary, id;
    select *
    into row
    from employee
             inner join jobs j on j.jobs_id = employee.job_id
    where employee_id = id;
    if row is null then
        raise notice 'An error with setting new salary. See the error message for more info.';
        raise exception 'No Employee with ID=%. Try another value.',
            id;
    end if;
    if row.max_salary < new_salary or
       row.min_salary > new_salary
    then
        raise notice 'An error with setting new salary. See the error message for more info.';
        raise exception 'Out of bound available salary values for job <<%>>! New salary should be between % and %',
            row.job_title,
            row.min_salary,
            row.max_salary;
    end if;
    update employee
    set salary = new_salary
    where employee_id = id;
    commit;
    raise notice 'Salary successfully updated!';
end;
$$;

-- #10
-- Триггер, реагирующий на добавление данных в таблицу jobs.
-- Триггер выполняется перед вставкой новой записи в таблицу jobs.
-- Он проверяет значение поля jobs_id: если оно не указано (is null),
-- то триггер сам генерирует его значение по схеме: 'j' + уникальный
-- номер из последовательности jobs_sec.
--
-- Создание  последовательности
create sequence jobs_sec increment by 1 start with 20;

-- Создание триггерной функции
create or replace function trigger_before_insert_into_jobs() returns trigger
as
$$
begin
    if new.jobs_id is null
    then
        new.jobs_id = 'j' || trim(to_char(nextval('jobs_sec'),'9999999'));
    end if;
    return new;
end
$$
    language plpgsql;

-- Создание триггера
create trigger jobs_fill
    before insert
    on jobs
    for each row
execute procedure trigger_before_insert_into_jobs();

-- #11
-- Запрос, выводящий название департаментов, в которых зарплаты сотрудников
-- не больше и не меньше определенного значения.
select distinct d.department_name
from departments d
         inner join employee e on d.department_id = e.department_id
where e.salary > 3500
  and e.salary < 50000;