package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Path.of(args[0]);
        String pattern = args[1];
        search(start, path -> path.toFile().getName().endsWith(pattern)).forEach(System.out::println);
    }

    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException(args[0] + "is not a directory!");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Search pattern must start with \".\"");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}