package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] entries = line.split("=", 2);
                    if (line.startsWith("=")
                            || entries.length < 2
                            || !line.contains("=")
                            || entries[1].isEmpty()) {
                        throw new IllegalArgumentException("Incorrect line " + line);
                    }
                    values.put(entries[0], entries[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("data/app.properties");
        System.out.println(config.toString());
        System.out.println();
    }

}