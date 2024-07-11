package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.List;

public class TestJson {
    public static void main(String[] args) {
        Book book = new Book(true, 360, "Лабиринт отражений",
                new Author("Лукьяненко", 1968), new String[]{"Фантастика", "Киберпанк"});
        System.out.println(new JSONObject(book).toString());

        JSONObject jsonAuthor = new JSONObject(new Author("Васильев", 1955));
        List<String> genres = Arrays.asList("Фэнтези", "Юмор");
        JSONArray jsonGenres = new JSONArray(genres);
        JSONObject jsonBook = new JSONObject();
        jsonBook.put("isAvailable", book.isAvailable());
        jsonBook.put("numberOfPages", book.getNumberOfPages());
        jsonBook.put("title", book.getTitle());
        jsonBook.put("author", jsonAuthor);
        jsonBook.put("genres", jsonGenres);

        System.out.println(jsonBook.toString());
    }
}
