package ru.job4j.serialization.json;

public class Author {
    private String name;
    private int birthYear;

    public Author(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Author{"
                + "name='" + name + '\''
                + ", birthYear=" + birthYear
                + '}';
    }
}
