package ru.job4j.ood.lsp.food.utils;

import ru.job4j.ood.lsp.food.Food;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Calculator {
    public static int dayDiff(Food food) {
        long diff = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long daysToExpire = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate());
        return (int) ((diff - daysToExpire) * 100 / diff);
    }
}
