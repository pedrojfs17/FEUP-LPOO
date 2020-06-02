package com.g51.pokemon.model.menu;

import com.g51.pokemon.model.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int option;
    private final int maxOptions;

    private List<Observer> observers;

    public Menu(int maxOptions) {
        this.option = 0;
        this.maxOptions = maxOptions - 1;
        this.observers = new ArrayList<>();
    }

    public int getOption() {return this.option;}

    public void nextOption() {
        this.option++;
        if (this.option == maxOptions + 1) this.option = 0;
        notifyObservers();
    }

    public void prevOption() {
        this.option--;
        if (this.option == -1) this.option = maxOptions;
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
