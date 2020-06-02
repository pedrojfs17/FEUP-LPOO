package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.controller.states.PauseState;
import com.g51.pokemon.controller.iooperations.Saver;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveCommand implements Command {
    @Override
    public void execute(Game game) {
        game.playSoundEffect("SaveSE");

        GameState newState = game.getState();
        game.setState(((PauseState)newState).getPrevState());

        Saver saver;
        try {

            FileOutputStream playerOutputStream= new FileOutputStream("src/main/java/com/g51/pokemon/model/database/player.txt");
            FileOutputStream pokedexOutputStream= new FileOutputStream("src/main/java/com/g51/pokemon/model/database/pokedex.txt");
            FileOutputStream routeOutputStream= new FileOutputStream("src/main/java/com/g51/pokemon/model/database/route.txt");
            FileOutputStream buildingOutputStream= new FileOutputStream("src/main/java/com/g51/pokemon/model/database/building.txt");
            saver = new Saver();
            saver.savePlayer(game.getPlayer(),playerOutputStream);
            saver.savePokedex(game.getPokedex(),pokedexOutputStream);
            saver.saveRoute(game.getRoute(),routeOutputStream);
            saver.saveBuilding(game.getBuilding(),buildingOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        game.changed();
    }
}
