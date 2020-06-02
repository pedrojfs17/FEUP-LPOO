package com.g51.pokemon.model.map;

import com.g51.pokemon.model.creator.TerrainCreator;
import com.g51.pokemon.model.observer.Observer;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.position.Position;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Route extends Movable implements Observer {
    private int routeNum;
    private TerrainCreator terrainCreator;

    private int width;
    private int height;

    private List<Wall> walls;
    private List<Grass> grasses;
    private List<BuildingDoor> doors;

    private List<Observer> observers;

    private boolean battleFound = false;
    private int inBuilding = -1;

    public Route(Player player, int width, int height, int routeNum) {
        this.routeNum = routeNum;

        this.width = width;
        this.height = height;

        this.player = player;

        this.walls = new ArrayList<>();
        this.grasses = new ArrayList<>();
        this.doors = new ArrayList<>();

        this.observers = new ArrayList<>();
    }

    public void setTerrainCreator(TerrainCreator terrainCreator) {
        this.terrainCreator = terrainCreator;
    }

    public void setWalls(List<Wall> walls) {this.walls = walls;}

    public void setGrasses(List<Grass> grasses) {this.grasses = grasses;}

    public void setDoors(List<BuildingDoor> doors) {this.doors = doors;}

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addElement(Element element) {
        if (element instanceof Player) player = (Player) element;
        else if (element instanceof Wall) walls.add((Wall) element);
        else if (element instanceof Grass) grasses.add((Grass) element);
        else if (element instanceof BuildingDoor) doors.add((BuildingDoor) element);

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

    public void movePlayerToBuildingDoor() {
        if (inBuilding < 0) { // Came from a lost battle
            inBuilding = 0;
            healPokemons();
        }
        player.setPosition(doors.get(inBuilding).getPosition().down());
        inBuilding = -1;
    }

    public void healPokemons() {
        for (Pokemon c : player.getTeam()) {
            c.resetHealth();
        }
    }

    public boolean canMove(Position position) {
        if (position.getX() < 0) {
            player.setPosition(new Position(99, position.getY()));
            changeRoute(routeNum - 1);
            return false;
        }
        else if (position.getX() >= width) {
            player.setPosition(new Position(0, position.getY()));
            changeRoute(routeNum + 1);
            return false;
        }

        if (position.getY() < 0) {
            player.setPosition(new Position(position.getX(), 39));
            changeRoute(routeNum - 2);
            return false;
        }
        else if (position.getY() >= height) {
            player.setPosition(new Position(position.getX(), 0));
            changeRoute(routeNum + 2);
            return false;
        }

        Wall wall = (Wall) getCollidingElement(position, walls);
        if (wall != null) return false;

        Grass grass = (Grass) getCollidingElement(position, grasses);
        if (grass != null) this.walkInGrasses();

        BuildingDoor door = (BuildingDoor) getCollidingElement(position, doors);
        if (door != null) this.inBuilding = doors.indexOf(door);

        return true;
    }

    private void walkInGrasses() {
        Random random = new Random();
        int number = random.nextInt(101);

        if (number < 10) this.changeBattleFound();
    }

    public boolean isBattleFound(){
        return battleFound;
    }

    public void changeBattleFound(){
        this.battleFound=!this.battleFound;
    }

    public void changeRoute(int routeNum) {
        this.routeNum = routeNum;
        terrainCreator.changeTerrain(this, routeNum);

        this.notifyObservers();
    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        elements.addAll(walls);
        elements.addAll(grasses);
        elements.addAll(doors);
        elements.add(player);

        return elements;
    }

    public int getRouteNum() {
        return routeNum;
    }

    public List<Wall> getWalls() {return this.walls;}

    public List<Grass> getGrasses() {return this.grasses;}

    public List<BuildingDoor> getDoors() {return this.doors;}

    public Building getBuilding() {return this.terrainCreator.getBuilding();}

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

    public int inBuilding() {
        return inBuilding;
    }

    public void changeInBuilding(){
        this.inBuilding=-1;
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        routeNum = (int) inputStream.readObject();
        width = (int) inputStream.readObject();
        height = (int) inputStream.readObject();
        walls = (List<Wall>) inputStream.readObject();
        grasses = (List<Grass>) inputStream.readObject();
        doors = (List<BuildingDoor>) inputStream.readObject();
        terrainCreator = (TerrainCreator) inputStream.readObject();
        observers = new ArrayList<>();
        battleFound = (boolean)inputStream.readObject();
        inBuilding = (int)inputStream.readObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(routeNum);
        outputStream.writeObject(width);
        outputStream.writeObject(height);
        outputStream.writeObject(walls);
        outputStream.writeObject(grasses);
        outputStream.writeObject(doors);
        outputStream.writeObject(terrainCreator);
        outputStream.writeObject(battleFound);
        outputStream.writeObject(inBuilding);
    }
}
