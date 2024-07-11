package ru.job4j.serialization.json;

import java.util.Arrays;

public class Book {
    private boolean isAvailable;
    private int numberOfPages;
    private String title;
    private Author author;
    private String[] genres;

    public Book(boolean isAvailable, int numberOfPages, String title, Author author, String[] genres) {
        this.isAvailable = isAvailable;
        this.numberOfPages = numberOfPages;
        this.title = title;
        this.author = author;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Book{"
                + "isAvailable=" + isAvailable
                + ", numberOfPages=" + numberOfPages
                + ", title='" + title + '\''
                + ", author=" + author
                + ", genres=" + Arrays.toString(genres)
                + '}';
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String[] getGenres() {
        return genres;
    }
}
