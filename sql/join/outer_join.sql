CREATE TABLE departments
(
    id   serial primary key,
    name varchar(255)
);

CREATE TABLE employees
(
    id            serial primary key,
    name          varchar(255),
    department_id int references departments (id)
);

INSERT INTO departments(name)
VALUES ('Бэк офис'),
       ('Фронт офис'),
       ('IT'),
       ('Бухгалтерия');

INSERT INTO employees (name, department_id)
VALUES ('Алексей Иванов', 1),
       ('Мария Петрова', 1),
       ('Сергей Сидоров', 1),
       ('Ольга Кузнецова', 2),
       ('Иван Смирнов', 2),
       ('Наталья Орлова', 2),
       ('Дмитрий Лебедев', 2),
       ('Анна Морозова', 3),
       ('Юрий Новиков', 3),
       ('Ксения Белова', 3),
       ('Елена Александрова', 3),
       ('Виктор Тихонов', 3),
       ('Ольга Васильева', 1),
       ('Николай Павлов', 1),
       ('Ирина Захарова', 2);



SELECT *
FROM departments d
         LEFT JOIN employees e ON d.id = e.department_id;

SELECT *
FROM departments d
         RIGHT JOIN employees e ON d.id = e.department_id;

SELECT *
FROM departments d
         FULL JOIN employees e ON d.id = e.department_id;

SELECT *
FROM departments d
         CROSS JOIN employees e;


SELECT d.name AS Департамент
FROM departments d
         LEFT JOIN employees e ON d.id = e.department_id
WHERE e.id IS NULL;


--  эквивалентный результат LEFT JOIN и RIGHT JOIN
SELECT d.name AS Департамент, e.name AS Сотрудник
FROM departments d
         LEFT JOIN employees e ON d.id = e.department_id;

SELECT d.name AS Департамент, e.name AS Сотрудник
FROM employees e
         RIGHT JOIN departments d ON d.id = e.department_id;