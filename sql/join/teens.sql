CREATE TABLE teens
(
    id     serial primary key,
    name   varchar(255),
    gender varchar(6)
);

INSERT INTO teens(name, gender)
VALUES ('Николай', 'male'),
       ('Иван', 'male'),
       ('Михаил', 'male'),
       ('Ольга', 'female'),
       ('Мария', 'female'),
       ('Анна', 'female');

SELECT t1.name, t2.name
FROM teens AS t1
         CROSS JOIN teens AS t2
WHERE t1.gender != t2.gender
  AND t1.gender != 'female';