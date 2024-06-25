create table products
(
    id           serial primary key,
    name_product varchar(255)
);

create table orders
(
    id     serial primary key,
    status varchar(255),
    create timestamp
);

create table products_orders
(
    id         serial primary key,
    product_id int references products (id),
    order_id   int references orders (id)
);
