package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.RouteState;

public class StartCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("PauseSE");
        game.changeBackgroundMusic("Route");

        RouteState newState = new RouteState(game.getRoute());
        newState.addObserver(game);
        game.setState(newState);
        game.changed();
    }
}
