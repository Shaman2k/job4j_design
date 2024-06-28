package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {

        final Book book = new Book(true, 360, "Лабиринт отражений",
                new Author("Лукьяненко", 1968), new String[]{"Фантастика", "Киберпанк"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(book));

        /* Создаём новую json-строку с модифицированными данными*/
        final String bookJson =
                "{"
                        + "\"isAvailable\":false,"
                        + "\"numberOfPages\":550,"
                        + "\"title\":\"Сердца и Моторы\","
                        + "\"author\":{"
                        + "\"name\":\"Васильев\","
                        + "\"birthYear\":1967"
                        + "},"
                        + "\"genres\":"
                        + "[\"Фантастика\",\"Фентези\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        final Book newBook = gson.fromJson(bookJson, Book.class);
        System.out.println(newBook);
    }
}
