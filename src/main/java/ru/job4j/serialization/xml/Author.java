package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {
    private String name;

    private int birthYear;

    public Author() {
    }

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
