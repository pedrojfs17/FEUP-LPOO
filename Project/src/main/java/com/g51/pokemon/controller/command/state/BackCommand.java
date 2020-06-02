package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.controller.states.MenuState;
import com.g51.pokemon.controller.states.PauseState;

public class BackCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("PauseSE");

        GameState state = game.getState();
        PauseState newState = ((MenuState)state).getPrev();
        game.setState(newState);
        game.changed();
    }
}
