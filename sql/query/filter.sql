SELECT p.name AS Название, p.price
FROM product p
         INNER JOIN type t on t.id = p.type_id
WHERE t.name = 'СЫР';


SELECT name AS Название
FROM product
WHERE name LIKE UPPER('%мороженое%');


SELECT name AS Название, expired_date AS "Годен до"
FROM product
WHERE expired_date < current_date;


SELECT name AS Название
FROM product
WHERE price = (SELECT MAX(price) FROM product);


SELECT t.name AS Тип, COUNT(t.name) AS Количество
FROM product p
         INNER JOIN type t on t.id = p.type_id
GROUP BY t.name;


SELECT p.name AS Название
FROM product p
         INNER JOIN type t on t.id = p.type_id
WHERE t.name = 'СЫР'
  AND t.name = 'МОЛОКО';


SELECT t.name AS Тип, COUNT(t.name) AS Количество
FROM product p
         INNER JOIN type t on t.id = p.type_id
GROUP BY t.name
HAVING COUNT(t.name) < 10;

SELECT t.name AS Тип, p.name AS Название, p.price AS Цена
FROM product p
         INNER JOIN type t on t.id = p.type_id;