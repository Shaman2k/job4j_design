package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.Food;

import java.util.function.Predicate;

public interface Store {
    void put(Food food);

    boolean contains(Food food);

    Predicate<Food> getCondition();
}
