package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.BagState;
import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.controller.states.PauseState;

public class BagCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("PauseSE");

        GameState state = game.getState();
        BagState newState = new BagState((PauseState) state, game.getPlayer());
        newState.addObserver(game);
        game.setState(newState);
        game.changed();
    }
}
