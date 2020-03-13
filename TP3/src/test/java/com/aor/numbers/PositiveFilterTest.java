package com.aor.numbers;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositiveFilterTest {
    @Test
    public void positive() {
        PositiveFilter filter = new PositiveFilter();

        assertFalse(filter.accept(-1));
        assertFalse(filter.accept(-6));
        assertTrue(filter.accept(2));
        assertTrue(filter.accept(10));
        assertFalse(filter.accept(0));
    }
}
