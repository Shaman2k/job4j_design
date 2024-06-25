create table authors
(
    id          serial primary key,
    name_author varchar(255)
);

create table books
(
    id        serial primary key,
    name_book varchar(255),
    author_id int references authors(id)
);