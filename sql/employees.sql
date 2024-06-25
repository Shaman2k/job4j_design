create database employees;
create table employee (
    id serial primary key,
    name varchar(255),
    salary integer,
    employment_date date
);
insert into employee (name, salary, employment_date) values ('Nikolay', 500, current_date);
update employee set name = 'Pavel', employment_date = '01/04/19';
delete from employee;