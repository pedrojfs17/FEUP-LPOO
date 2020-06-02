package com.g51.pokemon.model.creaturetests;

import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTests {
    @Test
    public void CreaturesTest() {
        Map<Integer, Attack> attackSet = new HashMap<>();
        attackSet.put(0, new Attack("Cut",50,0.95, Types.NORMAL));

        Pokemon firstCreature = new Pokemon("Pikachu", 100, 50, 30, 75, Types.NORMAL);
        firstCreature.setAttackSet(attackSet);
        Pokemon secondCreature = new Pokemon("Pichu", 100, 50, 30, 75, Types.NORMAL);
        secondCreature.setAttackSet(attackSet);

        assertEquals(Types.NORMAL, firstCreature.getType());

        assertEquals("Cut", firstCreature.getAttack(0).getName());
        assertEquals("Cut", secondCreature.getAttack(0).getName());

        assertEquals("Pichu", secondCreature.getName());
        assertEquals(100,secondCreature.getHealth());
        assertEquals(50, secondCreature.getAtk());
        assertEquals(30, secondCreature.getDef());
        assertEquals(75, secondCreature.getSpeed());

        assertEquals("Pichu  Health:100  type=NORMAL", secondCreature.toString());
        Pokemon copy=new Pokemon("Pichu", 100, 50, 30, 75, Types.NORMAL);
        copy.setAttackSet(attackSet);
        assertEquals(copy,secondCreature);


        secondCreature.applyDamage(10);
        assertEquals(90,secondCreature.getHealth());
        firstCreature.applyDamage(110);
        assertEquals(0,firstCreature.getHealth());
        assertTrue(firstCreature.isFainted());
        firstCreature.resetHealth();
        assertEquals(100,firstCreature.getHealth());

        assertEquals(firstCreature, firstCreature);
        assertEquals(firstCreature.hashCode(), firstCreature.hashCode());
        assertNotEquals(firstCreature.hashCode(), secondCreature.hashCode());
        assertNotEquals(firstCreature, secondCreature);

        firstCreature.gainExperience(100);
        assertEquals(5,firstCreature.getLevel());
        assertEquals(111,firstCreature.getOg_health());
        assertEquals(111,firstCreature.getHealth());
        assertEquals(57,firstCreature.getAtk());
        assertEquals(34,firstCreature.getDef());
        assertEquals(83,firstCreature.getSpeed());

        firstCreature.increaseLevel(10);
        assertEquals(15, firstCreature.getLevel());
    }
}
