package com.aor.refactoring.example3;

public class SimpleOrder {
    private double price;
    Discount discount;

    public SimpleOrder(double price) {
        this.price = price;
        this.discount = new NoDiscount();
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return discount.applyDiscount(price);
    }
}
