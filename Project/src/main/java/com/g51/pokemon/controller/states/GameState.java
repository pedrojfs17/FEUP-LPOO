package com.g51.pokemon.controller.states;

import com.googlecode.lanterna.screen.TerminalScreen;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.model.observer.Observer;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;

public abstract class GameState implements Observer {
    private List<Observer> observers;

    public GameState() {this.observers = new ArrayList<>(); }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.changed();
        }
    }

    public abstract void draw(TerminalScreen screen);

    public abstract Command getNextCommand(KeyStroke input);

    @Override
    public void changed() {
        this.notifyObservers();
    }
}
