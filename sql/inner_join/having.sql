create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

INSERT INTO devices(name, price)
VALUES ('Ноутбук', 7000),
       ('Монитор', 4000),
       ('ПК', 6000),
       ('Смартфон', 500),
       ('МФУ', 9000);
INSERT INTO people(name)
VALUES ('Иван'),
       ('Николай'),
       ('Александр'),
       ('Алексей');
INSERT INTO devices_people(device_id, people_id)
VALUES (1, 1),
       (1, 3),
       (2, 2),
       (2, 3),
       (2, 4),
       (3, 2),
       (3, 3),
       (3, 4),
       (4, 1),
       (4, 3),
       (4, 4),
       (5, 2);

SELECT avg(price)
FROM devices;

SELECT p.name AS Имя, avg(d.price) AS "Средняя цена"
FROM devices_people dp
         JOIN people p ON dp.people_id = p.id
         JOIN devices d ON dp.device_id = d.id
GROUP BY p.name;

SELECT p.name AS Имя, avg(d.price) AS "Средняя цена"
FROM devices_people dp
         JOIN people p ON dp.people_id = p.id
         JOIN devices d ON dp.device_id = d.id
GROUP BY p.name
HAVING avg(d.price) > 5000;
