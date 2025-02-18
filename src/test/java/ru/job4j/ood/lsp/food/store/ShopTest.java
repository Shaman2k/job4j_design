package ru.job4j.ood.lsp.food.store;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ShopTest {

    @Test
    void whenExpiredTodayThenDiscount() {
        Store shop = new Shop();
        Food bread = new Food("Bread",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 23),
                10, 20);
        shop.put(bread);
        assertThat(bread.getPrice()).isEqualTo(8);
    }

    @Test
    void whenFreshThenNotDiscount() {
        Store shop = new Shop();
        Food bread = new Food("Bread",
                LocalDate.of(2025, 1, 20),
                LocalDate.of(2025, 2, 23),
                10, 20);
        shop.put(bread);
        assertThat(bread.getPrice()).isEqualTo(10);
    }
}