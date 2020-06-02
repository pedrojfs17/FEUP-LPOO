package com.g51.pokemon.model;

import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.map.Element;
import com.g51.pokemon.model.map.Grass;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.map.Wall;
import org.junit.jupiter.api.Test;
import com.g51.pokemon.model.position.Position;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementTests {
    @Test
    public void ElementTest() {
        Player player = new Player(10, 100);
        assertEquals(new Position(10, 100), player.getPosition());

        player.setPosition(new Position(100, 10));
        assertEquals(new Position(100, 10), player.getPosition());

        Pokemon crit = new Pokemon("Test",0,0,0,0, Types.NORMAL);
        player.addCreature(crit);

        assertEquals("Test",player.getCreature(0).getName());
    }

    @Test
    public void GrassTest(){
        Element grass = new Grass(10,10);
        assertEquals(new Position(10,10),grass.getPosition());

        grass.setPosition(new Position(20,20));
        assertEquals(new Position(20,20),grass.getPosition());
    }

    @Test
    public void WallTest(){
        Element wall = new Wall(10,10);
        assertEquals(new Position(10,10),wall.getPosition());

        wall.setPosition(new Position(20,20));
        assertEquals(new Position(20,20),wall.getPosition());
    }
}
