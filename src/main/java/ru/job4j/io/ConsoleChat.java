package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean isActive = true;
        boolean isAnswer = true;
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        System.out.println(answers.size());
        Random random = new Random();
        System.out.println("Добро пожаловать в чат");
        while (isActive) {
            String question = scanner.nextLine();
            switch (question) {
                case STOP:
                    isAnswer = false;
                    break;
                case OUT:
                    isAnswer = false;
                    isActive = false;
                    break;
                case CONTINUE:
                    isAnswer = true;
                    break;
                default:
            }
            log.add(question);
            if (isAnswer) {
                String answer = answers.get(random.nextInt(answers.size()));
                System.out.println(answer);
                log.add(answer);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            return reader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/botlog.txt", "data/answers.txt");
        consoleChat.run();
    }
}

