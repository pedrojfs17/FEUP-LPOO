package com.g51.pokemon.model.store;

import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String message;
    private Player player;

    private List<Observer> observers;

    public Store(Player player) {
        this.player = player;
        this.message = "Money: " + player.getMoney();

        this.observers = new ArrayList<>();
    }

    public String getMessage() {
        return this.message;
    }

    public int getPlayerMoney() {return this.player.getMoney();}

    public void buy(int amount) {
        if(this.player.getMoney() >= amount) {
            this.player.subtractMoney(amount);
            this.player.addPokeball();
            this.message = "You bought a Pokeball! Current Pokeballs: " + player.getPokeballs();
        }
        else {
            this.message = "You don't have enough money!";
        }
        notifyObservers();
    }

    public void sell(int amount) {
        if(this.player.getPokeballs() > 0) {
            this.player.addMoney(amount);
            this.player.subPokeball();
            this.message = "You sold a pokeball! Current pokeballs: " + player.getPokeballs();
        }
        else {
            this.message = "You don't have pokeballs!";
        }
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.changed();
        }
    }
}
