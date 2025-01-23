package ru.job4j.ood.lsp.food.store;

import static ru.job4j.ood.lsp.food.utils.Calculator.dayDiff;

public class Warehouse extends AbstractStore {

    public Warehouse() {
        setCondition(food -> dayDiff(food) < 25);
    }

}
