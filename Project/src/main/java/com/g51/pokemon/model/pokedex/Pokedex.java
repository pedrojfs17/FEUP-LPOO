package com.g51.pokemon.model.pokedex;

import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.database.CreatureDatabase;
import com.g51.pokemon.model.observer.Observer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Pokedex implements Serializable {
    private CreatureDatabase database;
    private Map<Pokemon, Boolean> pokemons;
    private int initialPos;
    private List<Observer> observers;

    public Pokedex(CreatureDatabase database) {
        this.pokemons = new LinkedHashMap<>();
        this.observers = new ArrayList<>();
        this.initialPos = 0;
        this.database = database;

        readDatabase(database.getCreatures());
    }

    private void readDatabase(List<Pokemon> database) {
        for (Pokemon c : database) {
            pokemons.put(c, false);
        }
    }

    public void addPokemon(Pokemon creature) {
        pokemons.replace(creature, true);
    }

    public List<Pokemon> getSeenCreatures() {
        List<Pokemon> creatures = new ArrayList<>();
        Set<Pokemon> keys = pokemons.keySet();

        for (Pokemon c: keys)
            if (pokemons.get(c)) creatures.add(c);

        return creatures;
    }

    public List<Pokemon> getCreatures() {
        List<Pokemon> creatures = new ArrayList<>();
        Set<Pokemon> keys = pokemons.keySet();

        int i = 0;
        for (Pokemon c: keys) {
            if (i >= initialPos && i < initialPos + 8) {
                creatures.add(pokemons.get(c) ? c : new Pokemon("???", 0, 0, 0, 0, Types.UNKNOWN));
            }
            i++;
        }

        return creatures;
    }

    public void nextPos() {
        if (initialPos < pokemons.size() - 8) {
            initialPos++;
            notifyObservers();
        }
    }

    public void previousPos() {
        if (initialPos > 0) {
            initialPos--;
            notifyObservers();
        }
    }

    public Pokemon getRandomPokemon() {
        Random r = new Random();
        return database.getCreature(r.nextInt(database.getDatabaseSize()));
    }

    public void addObserver(com.g51.pokemon.model.observer.Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.changed();
        }
    }


    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        database = (CreatureDatabase) inputStream.readObject();
        pokemons = (Map<Pokemon, Boolean>) inputStream.readObject();
        initialPos = 0;
        observers = new ArrayList<>();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(database);
        outputStream.writeObject(pokemons);
    }
}
