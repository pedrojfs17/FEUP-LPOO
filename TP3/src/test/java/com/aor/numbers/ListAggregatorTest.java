package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListAggregatorTest {
    private List<Integer> list;

    @Before
    public void listSetUp() {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);
    }

    @Test
    public void sum() {
        ListAggregator aggregator = new ListAggregator(list);

        int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public void max() {
        ListAggregator aggregator = new ListAggregator(list);

        int max = aggregator.max();

        assertEquals(5, max);
    }

    @Test
    public void min() {
        ListAggregator aggregator = new ListAggregator(list);

        int min = aggregator.min();

        assertEquals(1, min);
    }

    @Test
    public void distinct() {
        ListAggregator aggregator = new ListAggregator(list);

        int distinct = aggregator.distinct(new ListDeduplicator(list), new ListSorter(list));

        assertEquals(4, distinct);
    }

    @Test
    public void cornerCase() {
        list.clear();
        list.add(-1);
        list.add(-4);
        list.add(-5);

        ListAggregator aggregator = new ListAggregator(list);

        int max = aggregator.max();

        assertEquals(-1, max);
    }

    @Test
    public void distinct2() {
        class StubDeduplicator implements IListDeduplicator {
            public List<Integer> deduplicate(IListSorter listSorter){
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(4);
                return list;
            }
        }

        list.clear();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        IListDeduplicator deduplicator = Mockito.mock(IListDeduplicator.class);
        List<Integer> deduplicated = new ArrayList<>();
        deduplicated.add(1);
        deduplicated.add(2);
        deduplicated.add(4);
        Mockito.when(deduplicator.deduplicate(Mockito.any(IListSorter.class))).thenReturn(deduplicated);

        ListAggregator aggregator = new ListAggregator(list);

        int distinct = aggregator.distinct(deduplicator, new ListSorter(list));

        assertEquals(3, distinct);
    }
}