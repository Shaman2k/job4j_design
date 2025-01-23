package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.store.Store;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ControlQuality {
    private final Map<Predicate<Food>, Store> stores;

    public ControlQuality() {
        stores = new HashMap<>();
    }

    public void addStore(Store store) {
        stores.put(store.getCondition(), store);
    }

    public void put(Food food) {
        for (Predicate<Food> p : stores.keySet()) {
            if (p.test(food)) {
                stores.get(p).put(food);
                break;
            }
        }
    }
}
