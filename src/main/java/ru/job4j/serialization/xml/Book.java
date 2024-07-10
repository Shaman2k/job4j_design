package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @XmlAttribute
    private boolean isAvailable;
    @XmlAttribute
    private int numberOfPages;
    private String title;
    private Author author;
    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private String[] genres;

    public Book() {
    }

    public Book(boolean isAvailable, int numberOfPages, String title, Author author, String... genres) {
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
}
