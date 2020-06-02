package com.g51.pokemon.model;

import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.database.CreatureDatabase;
import com.g51.pokemon.model.observer.Observer;
import com.g51.pokemon.model.pokedex.Pokedex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PokedexTests {
    private CreatureDatabase database;
    private Pokemon pokemon0, unseenPokemon;
    private ArrayList<Pokemon> c, unseen, firstSeen, seen;

    @BeforeEach
    public void prepareDatabase() {
        pokemon0 = new Pokemon("Lickilicky0",110,85,95,50, Types.NORMAL);
        Pokemon pokemon1 = new Pokemon("Lickilicky1",111,85,95,50, Types.NORMAL);
        Pokemon pokemon2 = new Pokemon("Lickilicky2",112,85,95,50, Types.NORMAL);
        Pokemon pokemon3 = new Pokemon("Lickilicky3",113,85,95,50, Types.NORMAL);
        Pokemon pokemon4 = new Pokemon("Lickilicky4",114,85,95,50, Types.NORMAL);
        Pokemon pokemon5 = new Pokemon("Lickilicky5",115,85,95,50, Types.NORMAL);
        Pokemon pokemon6 = new Pokemon("Lickilicky6",116,85,95,50, Types.NORMAL);
        Pokemon pokemon7 = new Pokemon("Lickilicky7",117,85,95,50, Types.NORMAL);
        Pokemon pokemon8 = new Pokemon("Lickilicky8",118,85,95,50, Types.NORMAL);
        unseenPokemon = new Pokemon("???",0,0,0,0, Types.UNKNOWN);
        c = new ArrayList<>();
        c.add(pokemon0);
        c.add(pokemon1);
        c.add(pokemon2);
        c.add(pokemon3);
        c.add(pokemon4);
        c.add(pokemon5);
        c.add(pokemon6);
        c.add(pokemon7);
        c.add(pokemon8);

        unseen = new ArrayList<>();
        unseen.add(unseenPokemon);
        unseen.add(unseenPokemon);
        unseen.add(unseenPokemon);
        unseen.add(unseenPokemon);
        unseen.add(unseenPokemon);
        unseen.add(unseenPokemon);
        unseen.add(unseenPokemon);
        unseen.add(unseenPokemon);

        firstSeen = new ArrayList<>();
        firstSeen.add(pokemon0);
        firstSeen.add(unseenPokemon);
        firstSeen.add(unseenPokemon);
        firstSeen.add(unseenPokemon);
        firstSeen.add(unseenPokemon);
        firstSeen.add(unseenPokemon);
        firstSeen.add(unseenPokemon);
        firstSeen.add(unseenPokemon);

        seen = new ArrayList<>();
        seen.add(pokemon0);

        database = mock(CreatureDatabase.class);
        when(database.getDatabaseSize()).thenReturn(9);
        when(database.getCreatures()).thenReturn(c);
        when(database.getCreature(0)).thenReturn(pokemon0);
        when(database.getCreature(1)).thenReturn(pokemon0);
        when(database.getCreature(2)).thenReturn(pokemon0);
        when(database.getCreature(3)).thenReturn(pokemon0);
        when(database.getCreature(4)).thenReturn(pokemon0);
        when(database.getCreature(5)).thenReturn(pokemon0);
        when(database.getCreature(6)).thenReturn(pokemon0);
        when(database.getCreature(7)).thenReturn(pokemon0);
        when(database.getCreature(8)).thenReturn(pokemon0);
    }

    @Test
    public void PokedexTest() {
        Pokedex pokedex = new Pokedex(database);
        Observer observer = mock(Observer.class);
        pokedex.addObserver(observer);

        assertEquals(unseen, pokedex.getCreatures());
        assertEquals(pokemon0, pokedex.getRandomPokemon());

        pokedex.addPokemon(pokemon0);

        assertEquals(seen, pokedex.getSeenCreatures());

        assertEquals(firstSeen, pokedex.getCreatures());
        assertEquals(pokemon0, pokedex.getRandomPokemon());

        pokedex.nextPos();
        verify(observer, times(1)).changed();

        pokedex.previousPos();
        verify(observer, times(2)).changed();

        pokedex.previousPos();
        verify(observer, times(2)).changed();
    }
}
