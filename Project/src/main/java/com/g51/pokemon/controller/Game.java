package com.g51.pokemon.controller;

import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.model.battle.BattleMechanics;
import com.g51.pokemon.model.map.Building;
import com.g51.pokemon.model.map.Route;
import com.g51.pokemon.controller.command.*;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.view.MusicPlayer;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.observer.Observer;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.controller.states.GameState;
import java.io.IOException;

public class Game implements Observer {
    private GameState state;
    private Gui gui;
    private Player player;
    private Pokedex pokedex;
    private Route route;

    private MusicPlayer musicPlayer;

    public Game(GameState state, Gui gui, Player player, Pokedex pokedex, Route route, MusicPlayer musicPlayer) {
        this.state = state;
        this.player = player;
        this.pokedex = pokedex;
        this.route=route;
        this.gui = gui;
        state.addObserver(this);

        Pokemon c = pokedex.getRandomPokemon();
        this.player.addCreature(c);
        this.player.addPokeballs(1);
        pokedex.addPokemon(c);

        this.musicPlayer = musicPlayer;
    }

    public void start() throws IOException {
        changed();

        while (!gui.isFinished()) {
            Command command = gui.getNextCommand(state);
            command.execute(this);
        }
    }

    public Battle newBattle() {
        Pokemon c = pokedex.getRandomPokemon();
        pokedex.addPokemon(c);
        c.increaseLevel((route.getRouteNum() - 1) * 20 + 3);
        return new Battle(player,c, new BattleMechanics());
    }

    public void setState(GameState state) {this.state = state;}

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public GameState getState() {return this.state;}

    public Pokedex getPokedex() {return this.pokedex;}

    public Player getPlayer() {
        return player;
    }

    public Route getRoute() {
        return route;
    }

    public Building getBuilding() {return route.getBuilding();}

    public void changeBackgroundMusic(String musicName) {
        musicPlayer.stopMusic();
        musicPlayer.setBackgroundMusic(musicName);
        musicPlayer.startMusic();
    }

    public void playSoundEffect(String sound) {musicPlayer.playSoundEffect(sound);}

    public void pauseMusic() {musicPlayer.pauseMusic();}

    public void resumeMusic() {
        musicPlayer.resumeMusic();
    }

    @Override
    public void changed() {
        try {
            gui.draw(state);
        } catch (IOException ignored) { }
    }
}
