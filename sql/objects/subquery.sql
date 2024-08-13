CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES ('Иван', 'Иванов', 22, 'Россия'),
       ('Ольга', 'Петрова', 35, 'Белоруссия'),
       ('Никита', 'Смирнов', 22, 'Белоруссия'),
       ('Елена', 'Ковалева', 30, 'Казахстан'),
       ('Алексей', 'Морозов', 45, 'Россия');

SELECT *
FROM customers
WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id)
VALUES (2, 1),
       (15, 2),
       (21, 3),
       (25, 1),
       (3, 3),
       (7, 1);

SELECT *
FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);