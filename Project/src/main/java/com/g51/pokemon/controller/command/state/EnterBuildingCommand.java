package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.BuildingState;
import com.g51.pokemon.controller.states.GameState;

public class EnterBuildingCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("DoorSE");

        GameState state = game.getState();
        BuildingState newState = new BuildingState(game.getBuilding(), state);
        newState.addObserver(game);
        game.setState(newState);
        game.changed();
    }
}
