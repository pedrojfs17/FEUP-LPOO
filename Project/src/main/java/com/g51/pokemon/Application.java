package com.g51.pokemon;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.states.StartState;
import com.g51.pokemon.model.creator.TerrainCreator;
import com.g51.pokemon.model.database.CreatureDatabase;
import com.g51.pokemon.view.MusicPlayer;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.map.Route;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.controller.states.GameState;

import java.io.*;

public class Application {
    public static void main(String[] args) throws IOException {
        Player player = new Player(10, 10);
        Pokedex pokedex = new Pokedex(new CreatureDatabase());

        TerrainCreator terrainCreator = new TerrainCreator(player, 4);
        terrainCreator.loadRoutes();
        Route route = terrainCreator.createTerrain(1, player);

        GameState state = new StartState();

        Gui gui = new Gui();

        MusicPlayer musicPlayer = new MusicPlayer();

        Game game = new Game(state, gui, player, pokedex, route, musicPlayer);

        game.changeBackgroundMusic("StartMusic");

        game.start();
    }
}
