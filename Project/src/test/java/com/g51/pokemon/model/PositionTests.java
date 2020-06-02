package com.g51.pokemon.model;

import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.position.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTests {
    @Test
    public void PositionTest() {
        Position position = new Position(1, 2);
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());

        assertEquals(new Position(2, 2), position.right());
        assertEquals(new Position(0, 2), position.left());
        assertEquals(new Position(1, 1), position.up());
        assertEquals(new Position(1, 3), position.down());

        assertNotEquals(position, new Player(1, 2));
        assertEquals(position, position);
    }
}
