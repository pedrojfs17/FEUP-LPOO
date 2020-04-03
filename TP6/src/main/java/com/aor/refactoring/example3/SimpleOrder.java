package com.aor.refactoring.example3;

public class SimpleOrder {
    private double price;

    public SimpleOrder(double price) {
        this.price = price;
    }

    public void setDiscount(Discount discount) {
        this.price = discount.applyDiscount(price);
    }

    public double getTotal() {
        return price;
    }
}
