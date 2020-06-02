package com.g51.pokemon.controller;

import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.map.Route;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.controller.command.QuitCommand;
import com.g51.pokemon.view.MusicPlayer;
import com.g51.pokemon.view.gui.Gui;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameTest {
    @Test
    public void GameTest() throws IOException {
        GameState state = mock(GameState.class);

        Gui gui = mock(Gui.class);
        when(gui.isFinished()).thenReturn(false).thenReturn(false).thenReturn(true);
        when(gui.getNextCommand(state)).thenReturn(new QuitCommand(mock(Screen.class)));

        Pokedex pokedex = mock(Pokedex.class);
        when(pokedex.getRandomPokemon()).thenReturn(mock(Pokemon.class));

        Route route = mock(Route.class);

        MusicPlayer musicPlayer = mock(MusicPlayer.class);

        Game game = new Game(state, gui, mock(Player.class), pokedex, route, musicPlayer);

        game.start();

        verify(gui, times(1)).draw(state);

        game.changeBackgroundMusic("Test");
        verify(musicPlayer, times(1)).stopMusic();
        verify(musicPlayer, times(1)).setBackgroundMusic("Test");
        verify(musicPlayer, times(1)).startMusic();

        game.playSoundEffect("Test1");
        verify(musicPlayer, times(1)).playSoundEffect("Test1");

        game.pauseMusic();
        verify(musicPlayer, times(1)).pauseMusic();

        game.resumeMusic();
        verify(musicPlayer, times(1)).resumeMusic();
    }
}
