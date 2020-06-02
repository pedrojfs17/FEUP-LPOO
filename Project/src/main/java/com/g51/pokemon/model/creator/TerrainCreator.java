package com.g51.pokemon.model.creator;

import com.g51.pokemon.model.map.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TerrainCreator implements Serializable{
    private List<Route> allRoutes;
    private Building building;
    private int numRoutes;
    private Player player;

    public TerrainCreator(Player player, int numRoutes) {
        this.allRoutes = new ArrayList<>();
        this.numRoutes = numRoutes;
        this.player = player;
    }

    public void loadRoutes() throws IOException {
        for (int i = 1; i <= numRoutes; i++) {
            allRoutes.add(createTerrain(i, player));
        }
        building = createBuilding(player);
    }

    public Route createTerrain(int routeNum, Player player) throws IOException {
        Route route = new Route(player, 100, 40, routeNum);

        processFile(routeNum, route);

        route.setTerrainCreator(this);

        return route;
    }

    public Building createBuilding(Player player) throws IOException {
        Building building = new Building(player);

        processFile(0, building);

        return building;
    }

    public Route getRoute(int routeNum) {return allRoutes.get(routeNum - 1);}

    public Building getBuilding() {return this.building;}

    private void processFile(int routeNum, Movable map) throws IOException {
        List<String> lines = readLines(routeNum);

        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++){
                char c = lines.get(y).charAt(x);
                processChar(c, x, y, map);
            }
        }
    }

    private void processChar(char c, int x, int y, Movable map) {
        switch (c) {
            case 'W':
                map.addElement(new Wall(x, y));
                break;
            case 'C':
                map.addElement(new Grass(x, y));
                break;
            case 'D':
                map.addElement(new BuildingDoor(x, y));
                break;
            case 'H':
                map.addElement(new Heal(x, y));
            default:
                break;
        }
    }

    public void changeTerrain(Route route, int routeNum) {
        Route newRoute = getRoute(routeNum);
        route.setWalls(newRoute.getWalls());
        route.setGrasses(newRoute.getGrasses());
        route.setDoors(newRoute.getDoors());
    }

    private static List<String> readLines(int routeNum) throws IOException {
        URL resource = TerrainCreator.class.getResource("/routes/" + routeNum + ".lvl");
        BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
        List<String> lines = new ArrayList<>();

        for (String line; (line = reader.readLine()) != null; )
            lines.add(line);

        return lines;
    }
}
