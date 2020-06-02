package com.g51.pokemon.model.creaturetests;

import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttackTests {
    @Test
    public void AttackTest() {
        Attack attack = new Attack("Cut",50,0.95, Types.NORMAL);

        assertEquals("Cut", attack.getName());
        assertEquals(50, attack.getDamage());
        assertEquals(0.95, attack.getAccuracy(), 0.01);
        assertEquals(Types.NORMAL, attack.getType());

        assertNotEquals(attack, new Attack("Cut2", 50, 0.95, Types.NORMAL));

        assertEquals("Cut  Damage: 50  Type=NORMAL",attack.toString());

        Pokemon creat = new Pokemon("Test",100,10,10,10,Types.NORMAL);
        attack.useAttack(10,creat);
        assertEquals(90,creat.getHealth());
    }
}
