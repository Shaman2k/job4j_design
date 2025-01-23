package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.Food;

import java.util.function.Predicate;

import static ru.job4j.ood.lsp.food.utils.Calculator.dayDiff;

public class Shop extends AbstractStore {
    private final Predicate<Food> predicateShopDiscount = food -> dayDiff(food) > 75 && dayDiff(food) <= 100;

    public Shop() {
        setCondition(food -> dayDiff(food) >= 25 && dayDiff(food) <= 100);
    }

    @Override
    public void put(Food food) {
        if (predicateShopDiscount.test(food)) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
        }
        super.put(food);
    }
}
