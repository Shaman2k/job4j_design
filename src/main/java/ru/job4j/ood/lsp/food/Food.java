package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class Food {
    private final String name;
    private final LocalDate createDate;
    private final LocalDate expiryDate;
    private final int discount;
    private int price;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
