package com.aor.numbers;

import java.util.ArrayList;
import java.util.List;

public class ListFilter {
    private final List<Integer> list;

    public ListFilter(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> filter(IListFilter filter) {
        List<Integer> filtered = new ArrayList<>();

        for (Integer number : list) {
            if (filter.accept(number))
                filtered.add(number);
        }

        return filtered;
    }
}
