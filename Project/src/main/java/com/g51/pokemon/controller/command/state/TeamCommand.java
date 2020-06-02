package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.controller.states.PauseState;
import com.g51.pokemon.controller.states.TeamState;

public class TeamCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("PauseSE");

        GameState state = game.getState();
        TeamState newState = new TeamState((PauseState) state, game.getPlayer());
        newState.addObserver(game);
        game.setState(newState);
        game.changed();
    }
}
