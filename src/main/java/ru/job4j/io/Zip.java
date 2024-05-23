package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final static String SOURCE = "d";
    private final static String EXCLUDE = "e";
    private final static String OUTPUT = "o";

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(target))) {
            for (Path path : sources) {
               System.out.println(path.toString());
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName argsName) {
        Path path = Paths.get(argsName.get(SOURCE));
        if (!Files.exists(path) && !Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Illegal source path %s", path.toAbsolutePath()));
        }
        if (!argsName.get(EXCLUDE).startsWith(".")) {
            throw new IllegalArgumentException("Extension must start with .");
        }
        if (!argsName.get(OUTPUT).endsWith(".zip")) {
            throw new IllegalArgumentException("Output filename must end with .zip");
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.validate(argsName);
        try {
            zip.packFiles(Search.search(Paths.get(
                    argsName.get(SOURCE)),
                    p -> !p.toString().endsWith(argsName.get(EXCLUDE))),
                    new File(argsName.get(OUTPUT))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}