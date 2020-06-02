package com.g51.pokemon.controller.commandtests;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.battle.*;
import com.g51.pokemon.controller.states.BattleState;
import com.g51.pokemon.controller.states.SwitchPokemonState;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.map.Route;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.view.MusicPlayer;
import com.g51.pokemon.view.gui.Gui;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class BattleCommandTests {

    @Test
    public void BattleCommandTest(){
        Battle battle = Mockito.mock(Battle.class);
        when(battle.catchPokemon()).thenReturn(false).thenReturn(true);

        SelectAttackCommand selectFirstAttackCommand = new SelectAttackCommand(battle,0);
        SelectAttackCommand selectSecondAttackCommand = new SelectAttackCommand(battle,1);
        SelectAttackCommand selectThirdAttackCommand = new SelectAttackCommand(battle,2);
        SelectAttackCommand selectFourthAttackCommand = new SelectAttackCommand(battle,3);
        CatchPokemonCommand catchPokemonCommand = new CatchPokemonCommand(battle);
        ChoosePokemonCommand choosePokemonCommand = new ChoosePokemonCommand(0,battle);
        RunCommand runCommand = new RunCommand(battle);
        OpponentTurnCommand opponentCommand = new OpponentTurnCommand(battle);
        SwitchPokemonCommand switchPokemonCommand = new SwitchPokemonCommand(battle);

        Game game = mock(Game.class);

        selectFirstAttackCommand.execute(game);
        verify(battle,times(1)).continueBattle(0);

        selectSecondAttackCommand.execute(game);
        verify(battle,times(1)).continueBattle(1);

        selectThirdAttackCommand.execute(game);
        verify(battle,times(1)).continueBattle(2);

        selectFourthAttackCommand.execute(game);
        verify(battle,times(1)).continueBattle(3);

        catchPokemonCommand.execute(game);
        verify(battle,times(1)).catchPokemon();
        verify(game, times(1)).playSoundEffect("CaptureFailedSE");
        catchPokemonCommand.execute(game);
        verify(battle,times(2)).catchPokemon();
        verify(game, times(1)).playSoundEffect("CaptureSE");

        runCommand.execute(game);
        verify(battle,times(1)).runBattle();
        verify(game, times(1)).playSoundEffect("RunAwaySE");

        opponentCommand.execute(game);
        verify(battle,times(5)).continueBattle(anyInt()); // 5 times (4 player attacks + opponent attack)

        SwitchPokemonState switchPokemonState = mock(SwitchPokemonState.class);
        when(game.getState()).thenReturn(switchPokemonState);
        doNothing().when(game).setRoute(mock(Route.class));
        choosePokemonCommand.execute(game);
        verify(game, times(2)).playSoundEffect("CaptureFailedSE");
        verify(battle,times(1)).switchPokemon(0);

        BattleState battleState = mock(BattleState.class);
        Pokedex pokedex = mock(Pokedex.class);
        Pokemon pokemon = mock(Pokemon.class);
        when(pokedex.getRandomPokemon()).thenReturn(pokemon);
        doNothing().when(pokemon).increaseLevel(5);
        Game game2 = new Game(battleState, mock(Gui.class), mock(Player.class), pokedex, mock(Route.class), mock(MusicPlayer.class));
        switchPokemonCommand.execute(game2);
        assertTrue(game2.getState() instanceof SwitchPokemonState);
    }
}
