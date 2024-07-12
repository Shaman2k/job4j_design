CREATE TABLE car_bodies
(
    id   serial primary key,
    name varchar(255)
);

CREATE TABLE car_engines
(
    id   serial primary key,
    name varchar(255)
);

CREATE TABLE car_transmissions
(
    id   serial primary key,
    name varchar(255)
);

CREATE TABLE cars
(
    id              serial primary key,
    name            varchar(255),
    body_id         int references car_bodies (id),
    engine_id       int references car_engines (id),
    transmission_id int references car_transmissions (id)
);

INSERT INTO car_bodies (name)
VALUES ('Sedan'),
       ('Coupe'),
       ('Hatchback'),
       ('SUV');

INSERT INTO car_engines (name)
VALUES ('Gas'),
       ('Electric'),
       ('Hybrid'),
       ('Diesel');


INSERT INTO car_transmissions (name)
VALUES ('Automatic'),
       ('Manual'),
       ('CVT');


INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('Car 1', 1, 1, 1),
       ('Car 2', 2, 2, 2),
       ('Car 3', 2, 2, 1),
       ('Car 4', 3, 1, 3);

SELECT cars.id,
       cars.name              AS car_name,
       car_bodies.name        AS body_name,
       car_engines.name       AS engine_name,
       car_transmissions.name AS transmission_name
FROM cars
         LEFT JOIN car_bodies ON cars.body_id = car_bodies.id
         LEFT JOIN car_engines ON cars.engine_id = car_engines.id
         LEFT JOIN car_transmissions ON cars.transmission_id = car_transmissions.id;

SELECT car_bodies.name
FROM car_bodies
         LEFT JOIN cars ON car_bodies.id = cars.body_id
WHERE cars.body_id IS NULL;

SELECT car_engines.name
FROM car_engines
         LEFT JOIN cars ON car_engines.id = cars.engine_id
WHERE cars.engine_id IS NULL;

SELECT    car_transmissions.name
FROM    car_transmissions
        LEFT JOIN    cars ON car_transmissions.id = cars.transmission_id
WHERE cars.transmission_id IS NULL;
