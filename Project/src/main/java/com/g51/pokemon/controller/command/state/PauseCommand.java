package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.controller.states.PauseState;

public class PauseCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("PauseSE");

        GameState state = game.getState();
        if (state instanceof PauseState) {
            game.setState(((PauseState) state).getPrevState());
            game.resumeMusic();
        }
        else {
            game.pauseMusic();
            PauseState newState = new PauseState(state);
            newState.addObserver(game);
            game.setState(newState);
        }
        game.changed();
    }
}
