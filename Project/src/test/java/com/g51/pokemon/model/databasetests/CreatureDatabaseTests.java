package com.g51.pokemon.model.databasetests;

import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.database.CreatureDatabase;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatureDatabaseTests {
    CreatureDatabase database;

    @BeforeProperty
    void prepareDatabase() {
        database = new CreatureDatabase();
    }

    @Property
    void checkPokemonCreation(@ForAll @IntRange(min = 0, max = 19) int pokemonNumber) {
        assertEquals(4,database.getCreature(pokemonNumber).getAttackSet().size());
        if (pokemonNumber < 5)
            assertEquals(Types.NORMAL,database.getCreature(pokemonNumber).getType());
        else if (pokemonNumber < 10)
            assertEquals(Types.FIRE,database.getCreature(pokemonNumber).getType());
        else if (pokemonNumber < 15)
            assertEquals(Types.WATER,database.getCreature(pokemonNumber).getType());
        else
            assertEquals(Types.GRASS,database.getCreature(pokemonNumber).getType());
    }

    @Test
    public void creatureDatabaseTest() {
        database = new CreatureDatabase();

        assertEquals(20, database.getDatabaseSize());
        assertEquals(20, database.getCreatures().size());
        assertEquals("Lickilicky",database.getCreature(0).getName());
        assertEquals(4,database.getCreature(0).getAttackSet().size());
        assertEquals(Types.NORMAL,database.getCreature(0).getAttackSet().get(0).getType());
    }
}
