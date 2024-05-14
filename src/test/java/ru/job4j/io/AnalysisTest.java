package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void whenTwoUnavailablePeriod(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.txt").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("300 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").hasToString(result.toString());
    }

    @Test
    void whenUnavailablePeriod(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.txt").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("10:57:01;11:02:02;").hasToString(result.toString());
    }

    @Test
    void whenNoUnavailablePeriod(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.txt").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("300 10:57:01");
            writer.println("200 10:58:01");
            writer.println("200 10:59:01");
            writer.println("300 11:01:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("").hasToString(result.toString());
    }
}