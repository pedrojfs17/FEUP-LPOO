package com.g51.pokemon.model.map;

import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.observer.Observer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player extends Element implements Observer {
    private List<Pokemon> team;
    private List<Observer> observers;
    private int pokeballs;
    private int money;


    public Player(int x, int y) {
        super(x,y);
        this.pokeballs=0;
        this.team=new ArrayList<>();
        this.money=0;
        this.observers=new ArrayList<>();
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void subtractMoney(int amount) {
        this.money = Math.max(this.money - amount, 0);
    }

    public int getMoney() {
        return money;
    }

    public int getTeamSize() {
        return this.team.size();
    }

    public int getPokeballs() {
        return pokeballs;
    }

    public void addPokeball() {
        this.pokeballs+=1;
    }

    public void subPokeball() { this.pokeballs-=1;}

    public void addPokeballs(int amount) {
        this.pokeballs += amount;
    }

    public void addCreature(Pokemon creature){
        if(team.size() < 6)
            team.add(creature);
    }

    public Pokemon getCreature(int index){
        return this.team.get(index);
    }

    public Pokemon getNextCreature() {
        for (int i = 1; i < team.size(); i++) {
            if (team.get(i).getHealth() > 0)
                return switchPokemon(i);
        }
        return null;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void changed() {
        this.notifyObservers();
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.changed();
        }
    }

    public Pokemon switchPokemon(int pokemonIndex) {
        Collections.swap(team, 0, pokemonIndex);
        return team.get(0);
    }
}
