package com.g51.pokemon.controller.iooperations;

import com.g51.pokemon.model.map.Building;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.map.Route;
import com.g51.pokemon.model.pokedex.Pokedex;
import java.io.*;

public class Saver {
    public void savePlayer(Player player, OutputStream fileOutputStream) throws IOException {
        ObjectOutputStream outputStream =new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
        outputStream.writeObject(player);
        outputStream.close();
        fileOutputStream.close();
    }

    public void savePokedex(Pokedex pokedex, OutputStream fileOutputStream) throws IOException {
        ObjectOutputStream outputStream =new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
        outputStream.writeObject(pokedex);
        outputStream.close();
        fileOutputStream.close();
    }

    public void saveRoute(Route route, OutputStream fileOutputStream) throws IOException {
        ObjectOutputStream outputStream =new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
        outputStream.writeObject(route);
        outputStream.close();
        fileOutputStream.close();
    }

    public void saveBuilding(Building building, OutputStream fileOutputStream) throws IOException {
        ObjectOutputStream outputStream =new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
        outputStream.writeObject(building);
        outputStream.close();
        fileOutputStream.close();
    }
}
