package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    private static final String DELIMITER_KEY = "delimiter";
    private static final String SOURCE_KEY = "path";
    private static final String OUT_KEY = "out";
    private static final String FILTER_KEY = "filter";

    public static void handle(ArgsName argsName) {
        String delimiter = argsName.get(DELIMITER_KEY);
        String sourcePath = argsName.get(SOURCE_KEY);
        String targetPath = argsName.get(OUT_KEY);
        String[] filter = argsName.get(FILTER_KEY).split(",");
        List<Integer> indexes = new ArrayList<>();
        List<String> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(sourcePath))) {
            List<String> headers = Arrays.asList(scanner.nextLine().split(delimiter));
            StringJoiner header = new StringJoiner(delimiter);

            for (String element : filter) {
                    int index = headers.indexOf(element);
                    if (index != -1) {
                        indexes.add(index);
                        header.add(headers.get(index));
                    }
            }
            result.add(header.toString());

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(delimiter);
                StringJoiner sj = new StringJoiner(delimiter);
                for (int index : indexes) {
                    sj.add(values[index]);
                }
                result.add(sj.toString());
            }

            if ("stdout".equals(targetPath)) {
                for (String line : result) {
                    System.out.println(line);
                }
            } else {
                try (PrintStream ps = new PrintStream(new FileOutputStream(targetPath))) {
                    for (String line : result) {
                        ps.println(line);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void validate(ArgsName argsName) {
        if (!argsName.get(SOURCE_KEY).endsWith(".csv")) {
            throw new IllegalArgumentException("File must be in CSV format.");
        }
        String out = argsName.get(OUT_KEY);
        if (!"stdout".equals(out) && !out.endsWith(".csv")) {
            throw new IllegalArgumentException("Output must be 'stdout' or a CSV file.");
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}