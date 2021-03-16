-- #11
select distinct d.department_name
from departments d
         inner join employee e on d.department_id = e.department_id
where e.salary > 3500
  and e.salary < 50000;