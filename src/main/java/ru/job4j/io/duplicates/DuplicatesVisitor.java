package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, Set<Path>> filenames = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        filenames.computeIfAbsent(fileProperty, k -> new HashSet<>()).add(file);
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        filenames.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e -> {
                    System.out.println(e.getKey().toString());
                    e.getValue().forEach(System.out::println);
                });
    }
}