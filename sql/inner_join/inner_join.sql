create table authors
(
    id          serial primary key,
    name_author varchar(255)
);

create table books
(
    id        serial primary key,
    name_book varchar(255),
    author_id int references authors (id)
);

INSERT INTO authors (name_author)
VALUES ('Толстой'),
       ('Достоевский'),
       ('Булгаков');

INSERT INTO books (name_book, author_id)
VALUES ('Война и мир', 1),
       ('Преступление и наказание', 2),
       ('Идиот', 2),
       ('Мастер и Маргарита', 3),
       ('Детство', 1);
INSERT INTO books (name_book)
VALUES ('Собачье сердце');

SELECT a.name_author AS Автор, b.name_book AS Название
FROM books AS b
         INNER JOIN authors AS a ON a.id = b.author_id
ORDER BY Автор DESC;

SELECT a.name_author AS Автор, COUNT(a.name_author) AS "Количество книг"
FROM books AS b
         INNER JOIN authors AS a ON a.id = b.author_id
GROUP BY a.name_author;

SELECT a.name_author AS Автор, COUNT(a.name_author) AS "Количество книг"
FROM books AS b
         INNER JOIN authors AS a ON a.id = b.author_id
GROUP BY a.name_author
HAVING COUNT(a.name_author) > 1;



