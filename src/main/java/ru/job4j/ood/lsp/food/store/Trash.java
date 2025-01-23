package ru.job4j.ood.lsp.food.store;

import static ru.job4j.ood.lsp.food.utils.Calculator.dayDiff;

public class Trash extends AbstractStore {
    public Trash() {
        setCondition(food -> dayDiff(food) > 100);
    }
}
