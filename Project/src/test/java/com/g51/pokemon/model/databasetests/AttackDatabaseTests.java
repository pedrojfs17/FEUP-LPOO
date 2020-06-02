package com.g51.pokemon.model.databasetests;

import com.g51.pokemon.model.database.AttackDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackDatabaseTests {
    @Test
    public void attackDatabaseTest() {
        AttackDatabase database = new AttackDatabase();

        assertEquals(16, database.getAttacks().size());

        assertEquals("Cut", database.getAttack(0).getName());
    }
}
