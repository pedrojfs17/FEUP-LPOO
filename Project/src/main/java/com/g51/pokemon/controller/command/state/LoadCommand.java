package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.BuildingState;
import com.g51.pokemon.controller.states.RouteState;
import com.g51.pokemon.controller.iooperations.Loader;
import com.g51.pokemon.model.map.Building;
import com.g51.pokemon.model.map.Route;
import java.io.FileInputStream;
import java.io.IOException;

public class LoadCommand implements Command {
    @Override
    public void execute(Game game) {
        int routenum;
        Route route=null;
        Building building=null;
        Loader loader;

        try {

            loader = new Loader();
            FileInputStream playerInputStream= new FileInputStream("src/main/java/com/g51/pokemon/model/database/player.txt");
            FileInputStream pokedexInputStream= new FileInputStream("src/main/java/com/g51/pokemon/model/database/pokedex.txt");
            FileInputStream routeInputStream= new FileInputStream("src/main/java/com/g51/pokemon/model/database/route.txt");
            FileInputStream buildingInputStream= new FileInputStream("src/main/java/com/g51/pokemon/model/database/building.txt");
            game.setPlayer(loader.loadPlayer(playerInputStream));
            game.setPokedex(loader.loadPokedex(pokedexInputStream));
            route=loader.loadRoute(routeInputStream);
            building=loader.loadBuilding(buildingInputStream);
            routenum=route.getRouteNum();
            game.getRoute().changeRoute(routenum);
            game.setRoute(route);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        RouteState newState = new RouteState(route);
        newState.addObserver(game);

        if(route.inBuilding()>-1){
            BuildingState buildingState = new BuildingState(building,newState);
            buildingState.addObserver(game);
            game.setState(buildingState);
        }
        else
            game.setState(newState);

        game.changed();
    }
}
