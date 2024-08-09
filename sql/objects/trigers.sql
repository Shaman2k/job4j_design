create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
    or replace function tax_row()
    returns trigger as
$$
BEGIN
    new.price = new.price + new.price * 0.2;
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create
    or replace function tax()
    returns trigger as
$$
BEGIN
    update products
    set price = price + price * 0.2
    where id in (select id from inserted);
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger tax_row_trigger
    before insert
    on products
    for each row
execute procedure tax_row();

create trigger tax_state_trigger
    after insert
    on products
    referencing new table as
        inserted
    for each statement
execute procedure tax();

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
    or replace function add_history_func()
    returns trigger as
$$
BEGIN
    insert into history_of_price(name, price, date)
    VALUES (new.name, new.price, now());
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger add_history
    after insert
    on products
    for each row
execute procedure add_history_func();