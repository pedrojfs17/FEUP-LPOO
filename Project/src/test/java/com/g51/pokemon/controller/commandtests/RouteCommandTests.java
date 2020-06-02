package com.g51.pokemon.controller.commandtests;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.route.*;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.map.Building;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.position.Position;
import com.g51.pokemon.model.map.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RouteCommandTests {
    private Route route;
    private Building building;
    private Player player;
    private Game game;

    @BeforeEach
    public void createTerrain() {
        player = new Player(10, 10);
        player.addCreature(new Pokemon("teste", 100,10,10,10, Types.NORMAL));
        route = new Route(player, 100, 100,1);
        building=new Building(player);
        game = mock(Game.class);
    }

    @Test
    public void MoveDownCommandTest() {
        MoveDownCommand command = new MoveDownCommand(route);
        command.execute(game);
        assertEquals(new Position(10, 11), route.getPlayerPosition());
        verify(game, times(0)).playSoundEffect("CollisionSE");
    }

    @Test
    public void MoveUpCommandTest() {
        MoveUpCommand command = new MoveUpCommand(route);
        command.execute(game);
        assertEquals(new Position(10, 9), route.getPlayerPosition());
        verify(game, times(0)).playSoundEffect("CollisionSE");
    }

    @Test
    public void MoveLeftCommandTest() {
        MoveLeftCommand command = new MoveLeftCommand(route);
        command.execute(game);
        assertEquals(new Position(9, 10), route.getPlayerPosition());
        verify(game, times(0)).playSoundEffect("CollisionSE");
    }

    @Test
    public void MoveRightCommandTest() {
        MoveRightCommand command = new MoveRightCommand(route);
        command.execute(game);
        assertEquals(new Position(11, 10), route.getPlayerPosition());
        verify(game, times(0)).playSoundEffect("CollisionSE");
    }

    @Test
    public void HealCommandTest() {
        HealCommand command = new HealCommand(building);
        player.getCreature(0).applyDamage(10);
        assertEquals(90,player.getCreature(0).getHealth());
        command.execute(game);
        assertEquals(100,player.getCreature(0).getHealth());
        verify(game, times(1)).playSoundEffect("CaptureSE");
    }
}
