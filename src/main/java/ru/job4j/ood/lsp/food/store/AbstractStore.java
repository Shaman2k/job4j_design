package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore implements Store {
    private  final List<Food> storage = new ArrayList<>();
    private Predicate<Food> condition;

    @Override
    public void put(Food food) {
        storage.add(food);
    }

    @Override
    public boolean contains(Food food) {
        return storage.contains(food);
    }

    @Override
    public Predicate<Food> getCondition() {
        return condition;
    }

    public void setCondition(Predicate<Food> condition) {
        this.condition = condition;
    }
}
