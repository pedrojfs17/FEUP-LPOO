package com.aor.refactoring.example3;

public class NoDiscount implements Discount {
    @Override
    public double applyDiscount(double price) {
        return price;
    }
}
