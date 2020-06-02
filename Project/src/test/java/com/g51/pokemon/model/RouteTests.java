package com.g51.pokemon.model;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.route.MoveDownCommand;
import com.g51.pokemon.controller.command.route.MoveLeftCommand;
import com.g51.pokemon.controller.command.route.MoveRightCommand;
import com.g51.pokemon.controller.command.route.MoveUpCommand;
import com.g51.pokemon.model.creator.TerrainCreator;
import com.g51.pokemon.model.map.*;
import com.g51.pokemon.model.pokemon.Pokemon;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import com.g51.pokemon.model.observer.Observer;
import com.g51.pokemon.model.position.Position;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RouteTests {
    Route map;
    Player p1;

    @Test
    public void RouteTest() throws IOException {
        Player player = new Player(10, 10);
        Pokemon p1 = mock(Pokemon.class);
        player.addCreature(p1);
        BuildingDoor buildingDoor = mock(BuildingDoor.class);
        when(buildingDoor.getPosition()).thenReturn(new Position(20,20));

        TerrainCreator creator = new TerrainCreator(player,4);
        creator.loadRoutes();

        Observer observer = mock(Observer.class);

        try {
            Route route = creator.createTerrain(1, player);

            assertEquals(100, route.getWidth());
            assertEquals(40, route.getHeight());

            route.addObserver(observer);
            verify(observer,times(0)).changed();

            route.changed();
            verify(observer, times(1)).changed();

            route.setTerrainCreator(creator);
            assertFalse(route.isBattleFound());
            route.changeBattleFound();
            assertTrue(route.isBattleFound());
            // Walk a little
            route.movePlayerTo(new Position(90, 20));
            assertEquals(new Position(90, 20), route.getPlayerPosition());
            assertFalse( route.canMove(new Position(10, 0)));
            assertFalse( route.canMove(new Position(10, 39)));
            assertFalse( route.canMove(new Position(0,0)));
            // Walk in Grass
            route.movePlayerTo(new Position(17,4));

            assertEquals(1753, route.getAllElements().size());

            // Change to route 2
            route.movePlayerTo(new Position(100, 20));
            assertEquals(new Position(0, 20), route.getPlayerPosition());
            assertEquals(2, route.getRouteNum());
            // Change to route 4
            route.movePlayerTo(new Position(49, 40));
            assertEquals(new Position(49, 0), route.getPlayerPosition());
            assertEquals(4, route.getRouteNum());
            // Change to route 3
            route.movePlayerTo(new Position(-1, 20));
            assertEquals(new Position(99, 20), route.getPlayerPosition());
            assertEquals(3, route.getRouteNum());
            // Change to route 1
            route.movePlayerTo(new Position(49, -1));
            assertEquals(new Position(49, 39), route.getPlayerPosition());
            assertEquals(-1,route.inBuilding());
            assertEquals(1,route.getRouteNum());

            route.addElement(buildingDoor);
            route.movePlayerTo(new Position(20,20));
            assertEquals(1,route.inBuilding());
            route.movePlayerToBuildingDoor();
            assertEquals(new Position(20,21),player.getPosition());

            // Heal
            route.healPokemons();
            verify(p1, times(1)).resetHealth();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeProperty
    public void setupMap() {
        p1 = new Player(2,2);
        map = new Route(p1, 100, 40, 1);
        map.setTerrainCreator(mock(TerrainCreator.class));
    }

    @Provide
    Arbitrary<List<Command>> commands() {
        return Arbitraries.integers().between(0, 3).map(i -> {
            if (i == 0) return new MoveDownCommand(map);
            if (i == 1) return new MoveLeftCommand(map);
            if (i == 2) return new MoveRightCommand(map);
            return new MoveUpCommand(map);
        }).list();
    }

    @Property
    public void playerMovementTest(@ForAll("commands") List<Command> commands) {
        for (Command c : commands) {
            c.execute(mock(Game.class));
            assertTrue(p1.getPosition().getX() >= 0);
            assertTrue(p1.getPosition().getY() >= 0);
            assertTrue(p1.getPosition().getX() < map.getWidth());
            assertTrue(p1.getPosition().getY() < map.getHeight());
        }
    }
}
