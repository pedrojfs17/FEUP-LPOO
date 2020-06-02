package com.g51.pokemon.model.map;

import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.observer.Observer;
import com.g51.pokemon.model.position.Position;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Building extends Movable implements Observer {
    private List<Wall> walls;
    private BuildingDoor door;
    private List<Heal> heals;

    private List<Observer> observers;

    private boolean leaveBuilding = false;
    private boolean healingPosition = false;

    public Building(Player player) {
        this.player = player;
        this.walls = new ArrayList<>();
        this.heals = new ArrayList<>();

        this.observers = new ArrayList<>();
    }

    public void addElement(Element element) {
        if (element instanceof Player) player = (Player) element;
        else if (element instanceof Wall) walls.add((Wall) element);
        else if (element instanceof BuildingDoor) door = (BuildingDoor) element;
        else if (element instanceof Heal) heals.add((Heal) element);

        this.notifyObservers();
    }

    public boolean movePlayerTo(Position position) {
        if (canMove(position)) {
            player.setPosition(position);
            this.notifyObservers();
            return true;
        }
        else {
            this.notifyObservers();
            return false;
        }

    }

    public boolean canMove(Position position) {
        Wall wall = (Wall) getCollidingElement(position, walls);
        if (wall != null) return false;

        if (position.equals(door.getPosition())) this.leaveBuilding = true;

        Heal heal = (Heal) getCollidingElement(position, heals);
        healingPosition = heal != null;

        return true;
    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>(walls);
        elements.add(door);
        elements.addAll(heals);
        elements.add(player);

        return elements;
    }

    public boolean isHealingPosition() {
        return healingPosition;
    }

    public boolean isLeavingBuilding() {
        return this.leaveBuilding;
    }

    public void changeLeaveBuilding() {
        this.leaveBuilding = !this.leaveBuilding;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.changed();
        }
    }

    @Override
    public void changed() {
        this.notifyObservers();
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        walls = (List<Wall>) inputStream.readObject();
        door = (BuildingDoor) inputStream.readObject();
        observers = new ArrayList<>();
        leaveBuilding =(boolean)inputStream.readObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(walls);
        outputStream.writeObject(door);
        outputStream.writeObject(leaveBuilding);
    }

    public void healPokemons() {
        for (Pokemon c : player.getTeam()) {
            c.resetHealth();
        }
    }

    public void cleanObservers() {
        this.observers = new ArrayList<>();
    }
}
