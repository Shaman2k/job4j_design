package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            output.write(multiply(1).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String multiply(int value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            sb.append(value);
            sb.append(" * ");
            sb.append(i);
            sb.append(" = ");
            sb.append(value * i);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}