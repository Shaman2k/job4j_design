package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String line;
            boolean isUnavailable = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if ("500".equals(parts[0]) || "400".equals(parts[0])) {
                    if (!isUnavailable) {
                        isUnavailable = true;
                        writer.append(parts[1]).append(";");
                    }
                } else {
                    if (isUnavailable) {
                        isUnavailable = false;
                        writer.append(parts[1]).append(";").append(System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
