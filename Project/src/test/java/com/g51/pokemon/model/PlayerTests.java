package com.g51.pokemon.model;

import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.map.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTests {
    @Test
    public void PlayerTest(){

        Player player = new Player(10,10);

        assertEquals(0,player.getTeamSize());
        assertTrue(player.getTeam().isEmpty());

        player.addMoney(100);
        assertEquals(100,player.getMoney());
        player.subtractMoney(100);
        assertEquals(0,player.getMoney());

        player.addPokeballs(10);
        player.addPokeball();
        assertEquals(11,player.getPokeballs());
        player.subPokeball();
        assertEquals(10,player.getPokeballs());

        Pokemon c0 = mock(Pokemon.class);
        when(c0.getHealth()).thenReturn(0);
        Pokemon c1 = mock(Pokemon.class);
        when(c1.getHealth()).thenReturn(10);

        player.addCreature(c0);
        assertEquals(1,player.getTeamSize());

        assertNull(player.getNextCreature());
        assertNull(player.getNextCreature());

        player.addCreature(c1);

        assertEquals(c1, player.getNextCreature());

        assertEquals(c1,player.getCreature(0));
        assertEquals(c0,player.getCreature(1));

        assertEquals(c0, player.switchPokemon(1));

        assertEquals(c0,player.getCreature(0));
        assertEquals(c1,player.getCreature(1));

        assertEquals(c1,player.getNextCreature());
        assertNull(player.getNextCreature());
    }
}
