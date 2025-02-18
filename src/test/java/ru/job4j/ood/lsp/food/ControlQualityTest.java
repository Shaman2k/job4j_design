package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.store.Shop;
import ru.job4j.ood.lsp.food.store.Store;
import ru.job4j.ood.lsp.food.store.Trash;
import ru.job4j.ood.lsp.food.store.Warehouse;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ControlQualityTest {

    private final ControlQuality controlQuality = new ControlQuality();
    private final Store warehouse = new Warehouse();
    private final Store shop = new Shop();
    private final Store trash = new Trash();

    @BeforeEach
    void setup() {
        controlQuality.addStore(warehouse);
        controlQuality.addStore(shop);
        controlQuality.addStore(trash);
    }

    @Test
    void whenAddExpiredFoodThenTrash() {
        Food bread = new Food("Bread",
                LocalDate.of(2025, 1, 20),
                LocalDate.of(2025, 1, 22),
                10, 20);

        controlQuality.put(bread);
        assertThat(warehouse.contains(bread)).isFalse();
        assertThat(shop.contains(bread)).isFalse();
        assertThat(trash.contains(bread)).isTrue();
    }

    @Test
    void whenExpiredTodayThenShop() {
        Food bread = new Food("Bread",
                LocalDate.of(2025, 1, 20),
                LocalDate.of(2025, 1, 23),
                10, 20);

        controlQuality.put(bread);
        assertThat(warehouse.contains(bread)).isFalse();
        assertThat(shop.contains(bread)).isTrue();
        assertThat(trash.contains(bread)).isFalse();
    }

    @Test
    void whenFreshThenWarehouse() {
        Food bread = new Food("Bread",
                LocalDate.of(2025, 1, 20),
                LocalDate.of(2025, 2, 25),
                10, 20);

        controlQuality.put(bread);
        assertThat(warehouse.contains(bread)).isTrue();
        assertThat(shop.contains(bread)).isFalse();
        assertThat(trash.contains(bread)).isFalse();
    }
}