package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListFilterTest {
    private List<Integer> list;
    @Before
    public void listSetUp() {
        list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(4);
        list.add(7);
        list.add(-3);
    }

    @Test
    public void filterPositive() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(4);
        expected.add(7);

        ListFilter filter = new ListFilter(list);
        List<Integer> filtered = filter.filter(new PositiveFilter());

        assertEquals(expected, filtered);
    }

    @Test
    public void filterDivisible() {
        List<Integer> expected = new ArrayList<>();
        expected.add(-2);
        expected.add(4);

        ListFilter filter = new ListFilter(list);
        List<Integer> filtered = filter.filter(new DivisibleByFilter(2));

        assertEquals(expected, filtered);
    }
}
