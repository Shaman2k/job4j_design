create table countries
(
    id           serial primary key,
    name_country varchar(255)
);

create table capitals
(
    id           serial primary key,
    name_capital varchar(255)
);

create table countries_capitals
(
    id         serial primary key,
    country_id int references countries (id) unique,
    capital_id int references capitals (id) unique
)

