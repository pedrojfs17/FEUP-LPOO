package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.BuildingState;
import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.controller.states.RouteState;

public class LeaveBuildingCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("DoorSE");

        BuildingState state = (BuildingState) game.getState();
        GameState routeState = state.getRouteState();
        ((RouteState)routeState).leftBuilding();
        game.setState(routeState);
        game.changed();
    }
}
