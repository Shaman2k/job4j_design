create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('Чужой', 15000, '1995-10-25'),
       ('Бармалей', 21000, '1951-11-16'),
       ('Catfish', 10, '1800-02-02'),
       ('телепузик', 50, '2005-12-01'),
       ('Собака', 15, '0001-03-18'),
       ('Лунтик', 25000, null);


SELECT *
FROM fauna
WHERE name LIKE '%fish%';

SELECT *
FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT *
FROM fauna
WHERE discovery_date IS NULL;

SELECT *
FROM fauna
WHERE discovery_date < '1950-01-01';
