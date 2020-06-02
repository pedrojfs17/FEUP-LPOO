package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.InstructionsState;
import com.g51.pokemon.controller.states.StartState;

public class MainMenuCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("PauseSE");

        StartState newState = ((InstructionsState)game.getState()).getPrev();
        game.setState(newState);
        game.changed();
    }
}
