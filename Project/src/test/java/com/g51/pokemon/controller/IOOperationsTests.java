package com.g51.pokemon.controller;

import com.g51.pokemon.controller.iooperations.Loader;
import com.g51.pokemon.controller.iooperations.Saver;
import com.g51.pokemon.model.database.CreatureDatabase;
import com.g51.pokemon.model.map.Building;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.map.Route;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IOOperationsTests {
    private ByteArrayOutputStream out;

    private Saver saver;
    private Loader loader;

    @BeforeEach
    public void SetupIOTests() {
        out = new ByteArrayOutputStream();
        saver = new Saver();
        loader = new Loader();
    }

    @Test
    public void IOPlayer() throws IOException, ClassNotFoundException {
        Player p1 = new Player(20, 20), p2;

        saver.savePlayer(p1, out);

        byte [] bArray = out.toByteArray();
        ByteArrayInputStream in = new ByteArrayInputStream(bArray);

        p2 = loader.loadPlayer(in);

        assertEquals(p1.getPosition(), p2.getPosition());
    }

    @Test
    public void IOPokedex() throws IOException, ClassNotFoundException {
        CreatureDatabase database = new CreatureDatabase();

        Pokedex p1 = new Pokedex(database), p2;
        Pokemon pokemon1 = p1.getRandomPokemon();
        p1.addPokemon(pokemon1);

        saver.savePokedex(p1, out);

        byte [] bArray = out.toByteArray();
        ByteArrayInputStream in = new ByteArrayInputStream(bArray);

        p2 = loader.loadPokedex(in);

        assertEquals(p1.getSeenCreatures().get(0).getName(), p2.getSeenCreatures().get(0).getName());
    }

    @Test
    public void IORoute() throws IOException, ClassNotFoundException {
        Player player = new Player(10, 15);
        Route r1 = new Route(player, 100, 40, 2), r2;

        saver.saveRoute(r1, out);

        byte [] bArray = out.toByteArray();
        ByteArrayInputStream in = new ByteArrayInputStream(bArray);

        r2 = loader.loadRoute(in);

        assertEquals(r1.getPlayerPosition(), r2.getPlayerPosition());
        assertEquals(r1.getRouteNum(), r2.getRouteNum());
    }

    @Test
    public void IOBuilding() throws IOException, ClassNotFoundException {
        Player player = new Player(10, 15);
        Building b1 = new Building(player), b2;

        saver.saveBuilding(b1, out);

        byte [] bArray = out.toByteArray();
        ByteArrayInputStream in = new ByteArrayInputStream(bArray);

        b2 = loader.loadBuilding(in);

        assertEquals(b1.getPlayerPosition(), b2.getPlayerPosition());
    }
}
