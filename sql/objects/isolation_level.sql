create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

INSERT INTO
    PRODUCTS (NAME, PRODUCER, COUNT, PRICE)
VALUES
    ('product_1', 'producer_1', 3, 50),
    ('product_2', 'producer_2', 15, 32),
    ('product_3', 'producer_3', 8, 115);

--первая сессия
begin transaction isolation level serializable;
select sum(count) from products;
update products set count = 26 where name = 'product_1';
commit;

--вторая сессия
begin transaction isolation level serializable;
select sum(count) from products;
update products set count = 26 where name = 'product_2';
commit;


DELETE FROM PRODUCTS;
ALTER SEQUENCE products_id_seq RESTART WITH 1;