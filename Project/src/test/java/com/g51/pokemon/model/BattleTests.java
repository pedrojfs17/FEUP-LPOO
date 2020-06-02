package com.g51.pokemon.model;

import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.model.battle.BattleMechanics;
import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.observer.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BattleTests {
    private Player player;
    private Attack attack, attack2;
    private Pokemon creature, creature1, creature2;
    private Battle battle;
    private BattleMechanics mec;

    @BeforeEach
    public void prepare(){
        player = Mockito.mock(Player.class);
        creature = Mockito.mock(Pokemon.class);
        creature1 = Mockito.mock(Pokemon.class);
        creature2 = Mockito.mock(Pokemon.class);
        attack = new Attack("test",10,10.0, Types.FIRE);
        attack2 = new Attack("test2",27,0.0, Types.NORMAL);
        player.addCreature(creature);
        when(player.getTeamSize()).thenReturn(6);
        when(player.getCreature(0)).thenReturn(creature);
        when(creature.getSpeed()).thenReturn(10);
        when(creature.getAtk()).thenReturn(1);
        when(creature.getDef()).thenReturn(1);
        when(creature.getOg_health()).thenReturn(100);
        when(creature.getHealth()).thenReturn(100);
        when(creature.getType()).thenReturn(Types.GRASS);
        when(creature.getAttack(0)).thenReturn(attack);
        when(creature.getName()).thenReturn("C0");
        when(creature1.getSpeed()).thenReturn(10);
        when(creature1.getAtk()).thenReturn(12);
        when(creature1.getDef()).thenReturn(20);
        when(creature1.getOg_health()).thenReturn(100);
        when(creature1.getHealth()).thenReturn(100);
        when(creature1.getType()).thenReturn(Types.GRASS);
        when(creature1.getAttack(0)).thenReturn(attack);
        when(creature1.getName()).thenReturn("C1");
        when(creature2.getSpeed()).thenReturn(0);
        when(creature2.getAtk()).thenReturn(12);
        when(creature2.getDef()).thenReturn(20);
        when(creature2.getOg_health()).thenReturn(10000).thenReturn(0);
        when(creature2.getHealth()).thenReturn(1);
        when(creature2.getType()).thenReturn(Types.GRASS);
        when(creature2.getAttack(0)).thenReturn(attack);
        when(creature2.getName()).thenReturn("C2");

        when(creature.getLevel()).thenReturn(1);
        when(creature1.getLevel()).thenReturn(2);
        when(creature2.getLevel()).thenReturn(3);

        mec = Mockito.mock(BattleMechanics.class);

        battle = new Battle(player,creature1, mec);
    }

    @Test
    public void BattleTest(){
        Observer observer = Mockito.mock(Observer.class);
        battle.addObserver(observer);
        verify(observer,times(0)).changed();
        battle.changed();
        verify(observer,times(1)).changed();

        assertEquals(creature,battle.getPlayerCreature());
        assertEquals(creature1,battle.getCreature());
        assertEquals(Battle.STATE.IN_PROGRESS,battle.getState());

        assertFalse(battle.endedBattle());
        assertTrue(battle.getTurn());

        when(mec.attemptHit(attack)).thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(false).thenReturn(true);
        when(mec.deliverRewards()).thenReturn(100);
        when(mec.payForLose()).thenReturn(50);

        when(creature1.isFainted()).thenReturn(false).thenReturn(false).thenReturn(true).thenReturn(false);
        when(creature.isFainted()).thenReturn(false).thenReturn(false).thenReturn(true).thenReturn(true);
        when(creature2.isFainted()).thenReturn(true);
        battle.continueBattle(0);
        assertEquals("C0 used test!",battle.getMessage());
        verify(creature1, times(1)).applyDamage(anyInt());
        battle.continueBattle(0);
        assertEquals("C1 used test!",battle.getMessage());
        verify(creature, times(1)).applyDamage(anyInt());
        battle.continueBattle(0);
        assertEquals("C0 missed!",battle.getMessage());
        battle.continueBattle(0);
        assertEquals("C1 missed!",battle.getMessage());
        battle.continueBattle(0);
        assertEquals("You won! You received 100$ and gained 0xp!",battle.getMessage());
        assertEquals(Battle.STATE.WIN, battle.getState());
        verify(creature,times(1)).gainExperience(anyInt());
        battle.setState(Battle.STATE.IN_PROGRESS);
        when(player.getNextCreature()).thenReturn(creature2).thenReturn(null);
        battle.continueBattle(0);
        assertEquals("C0 fainted!",battle.getMessage());
        assertEquals(creature2, battle.getPlayerCreature());
        battle.continueBattle(0);
        battle.continueBattle(0);
        verify(player, times(1)).subtractMoney(anyInt());
        assertEquals("C2 fainted! You lost! You paid 50$ for losing.",battle.getMessage());
        assertEquals(Battle.STATE.LOSE, battle.getState());
        battle.setState(Battle.STATE.IN_PROGRESS);

        // Can catch
        assertFalse(battle.canCatch());
        assertEquals("Your team is full!", battle.getMessage());
        when(player.getTeamSize()).thenReturn(1);
        when(player.getPokeballs()).thenReturn(0);
        assertFalse(battle.canCatch());
        assertEquals("You don't have pokeballs!", battle.getMessage());
        when(player.getPokeballs()).thenReturn(1);
        assertTrue(battle.canCatch());

        // Catch Pokemon
        when(player.getTeamSize()).thenReturn(6);
        assertFalse(battle.catchPokemon());
        verify(observer,times(10)).changed();
        assertTrue(battle.getTurn());

        when(player.getTeamSize()).thenReturn(1);
        when(mec.catchProbability(creature1)).thenReturn(false).thenReturn(true);
        assertFalse(battle.catchPokemon());
        assertEquals("Couldn't catch C1!", battle.getMessage());
        verify(player, times(1)).subPokeball();
        assertFalse(battle.getTurn());

        assertTrue(battle.catchPokemon());
        assertEquals("You caught C1! You received 100$!", battle.getMessage());
        verify(player, times(1)).addCreature(creature1);
        verify(player, times(2)).addMoney(100);
        assertEquals(Battle.STATE.WIN, battle.getState());
        battle.setState(Battle.STATE.IN_PROGRESS);

        // Run Battle
        when(mec.runProbability(creature2, creature1, 0)).thenReturn(false);
        when(mec.runProbability(creature2, creature1, 1)).thenReturn(true);
        battle.runBattle();
        assertEquals("You couldn't run away!",battle.getMessage());
        assertFalse(battle.getTurn());
        battle.runBattle();
        assertEquals("You escaped!",battle.getMessage());
        assertEquals(Battle.STATE.WIN, battle.getState());
        battle.setState(Battle.STATE.IN_PROGRESS);

        // Switch Pokemon
        when(player.switchPokemon(1)).thenReturn(creature2);
        battle.switchPokemon(1);
        assertEquals(creature2, battle.getPlayerCreature());
    }

    @Test
    public void BattleMechanicsTest(){
        BattleMechanics battleMechanics = new BattleMechanics();

        assertTrue(battleMechanics.runProbability(creature,creature1,0));
        assertFalse(battleMechanics.runProbability(creature2, creature1, 0));
        assertTrue(battleMechanics.runProbability(creature2, creature1, 10));

        assertTrue(battleMechanics.catchProbability(creature2));
        assertFalse(battleMechanics.catchProbability(creature2));

        assertTrue(battleMechanics.attemptHit(attack));
        assertFalse(battleMechanics.attemptHit(attack2));

        assertEquals(14, battleMechanics.experienceGained(creature));
        assertEquals(28, battleMechanics.experienceGained(creature1));
        assertEquals(42, battleMechanics.experienceGained(creature2));

        assertEquals(200, battleMechanics.payForLose(), 100);

        assertEquals(350, battleMechanics.deliverRewards(), 250);

        assertEquals(2, battleMechanics.calculateDamage(attack2, creature2, creature1));

        when(creature2.getSpeed()).thenReturn(0);
        assertEquals(1, battleMechanics.criticalHit(creature2));
        when(creature2.getSpeed()).thenReturn(1000);
        assertEquals(2, battleMechanics.criticalHit(creature2));

        assertEquals(2,battleMechanics.effectiveType(creature.getAttack(0),creature),0.01);
        when(creature.getType()).thenReturn(Types.FIRE);
        assertEquals(0.5,battleMechanics.effectiveType(creature.getAttack(0),creature),0.01);


        Attack attack1 = mock(Attack.class);
        when(attack1.getType()).thenReturn(Types.WATER);
        assertEquals(2,battleMechanics.effectiveType(attack1,creature),0.01);
        when(creature.getType()).thenReturn(Types.NORMAL);
        assertEquals(1,battleMechanics.effectiveType(attack1,creature),0.01);
        when(creature.getType()).thenReturn(Types.WATER);
        assertEquals(0.5,battleMechanics.effectiveType(attack1,creature),0.01);

        when(attack1.getType()).thenReturn(Types.GRASS);
        assertEquals(2,battleMechanics.effectiveType(attack1,creature),0.01);
        when(creature.getType()).thenReturn(Types.NORMAL);
        assertEquals(1,battleMechanics.effectiveType(attack1,creature),0.01);
        when(creature.getType()).thenReturn(Types.FIRE);
        assertEquals(0.5,battleMechanics.effectiveType(attack1,creature),0.01);

        when(creature.getType()).thenReturn(Types.NORMAL);
        assertEquals(1,battleMechanics.effectiveType(creature.getAttack(0),creature),0.01);

        when(attack1.getType()).thenReturn(Types.NORMAL);
        assertEquals(1,battleMechanics.effectiveType(attack1,creature),0.01);
    }
}
