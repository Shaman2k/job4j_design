create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

begin transaction;
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 22, 75);
savepoint first_savepoint;
delete from products where price = 75;
savepoint second_savepoint;
update products set count = 40 where producer = 'producer_2';
select * from products;
rollback to second_savepoint;
select * from products;
rollback to first_savepoint;
select * from products;
rollback;
select * from products;

