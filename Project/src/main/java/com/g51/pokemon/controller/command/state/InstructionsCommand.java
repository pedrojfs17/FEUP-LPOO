package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.InstructionsState;
import com.g51.pokemon.controller.states.StartState;

public class InstructionsCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("PauseSE");

        InstructionsState newState = new InstructionsState((StartState)game.getState());
        newState.addObserver(game);
        game.setState(newState);
        game.changed();
    }
}
