package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.controller.states.PauseState;
import com.g51.pokemon.controller.states.PokedexState;

public class PokedexCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("PokedexSE");

        GameState state = game.getState();
        PokedexState newState = new PokedexState((PauseState) state, game.getPokedex());
        newState.addObserver(game);
        game.setState(newState);
        game.changed();
    }
}
