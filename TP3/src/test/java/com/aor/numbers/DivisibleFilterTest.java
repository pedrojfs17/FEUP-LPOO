package com.aor.numbers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DivisibleFilterTest {
    @Test
    public void divisible() {
        DivisibleByFilter filter = new DivisibleByFilter(3);

        assertFalse(filter.accept(-1));
        assertFalse(filter.accept(-4));
        assertTrue(filter.accept(6));
        assertTrue(filter.accept(-9));
        assertTrue(filter.accept(12));
    }
}
