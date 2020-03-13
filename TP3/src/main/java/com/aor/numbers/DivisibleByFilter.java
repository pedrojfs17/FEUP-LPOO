package com.aor.numbers;

public class DivisibleByFilter implements IListFilter {
    private int divisor;

    public DivisibleByFilter(Integer num) {this.divisor = num;}

    @Override
    public boolean accept(Integer number) {
        return number % divisor == 0;
    }
}
