create table students
(
    id   serial primary key,
    name varchar(50)
);

insert into students (name)
values ('Иван Иванов'),
       ('Петр Петров');

create table authors
(
    id   serial primary key,
    name varchar(50)
);

insert into authors (name)
values ('Александр Пушкин'),
       ('Николай Гоголь');

create table books
(
    id        serial primary key,
    name      varchar(200),
    author_id integer references authors (id)
);

insert into books (name, author_id)
values ('Евгений Онегин', 1),
       ('Капитанская дочка', 1),
       ('Дубровский', 1),
       ('Мертвые души', 2),
       ('Вий', 2);

create table orders
(
    id         serial primary key,
    active     boolean default true,
    book_id    integer references books (id),
    student_id integer references students (id)
);

insert into orders (book_id, student_id)
values (1, 1),
       (3, 1),
       (5, 2),
       (4, 1),
       (5, 1),
       (2, 2);

create view show_students_with_books
as
select s.name as Студент, count(a.name) as Количество, a.name as Автор
from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
group by (s.name, a.name)
having count(a.name) >= 1 AND a.name like '%Н';

alter view show_students_with_books rename to show_students_with_books_start_whith_n;