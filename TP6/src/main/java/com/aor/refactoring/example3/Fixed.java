package com.aor.refactoring.example3;

public class Fixed implements Discount {
    private final int fixed;

    public Fixed(int fixed) {
        this.fixed = fixed;
    }

    @Override
    public double applyDiscount(double price) {
        return price - fixed;
    }
}
