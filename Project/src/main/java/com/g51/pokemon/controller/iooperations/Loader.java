package com.g51.pokemon.controller.iooperations;

import com.g51.pokemon.model.map.*;
import com.g51.pokemon.model.pokedex.Pokedex;
import java.io.*;

public class Loader{
    public Player loadPlayer(InputStream fileInputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(new BufferedInputStream(fileInputStream));
        Player player=(Player)inputStream.readObject();
        fileInputStream.close();
        inputStream.close();
        return player;
    }

    public Pokedex loadPokedex(InputStream fileInputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(new BufferedInputStream(fileInputStream));
        Pokedex pokedex=(Pokedex) inputStream.readObject();
        fileInputStream.close();
        inputStream.close();
        return pokedex;
    }

    public Route loadRoute(InputStream fileInputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(new BufferedInputStream(fileInputStream));
        Route route=(Route)inputStream.readObject();
        fileInputStream.close();
        inputStream.close();
        return route;
    }

    public Building loadBuilding(InputStream fileInputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(new BufferedInputStream(fileInputStream));
        Building building=(Building) inputStream.readObject();
        fileInputStream.close();
        inputStream.close();
        return building;
    }
}
