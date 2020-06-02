package com.g51.pokemon.controller.command.route;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.model.map.Building;

public class HealCommand implements Command {
    private Building building;

    public HealCommand(Building building) {
        this.building = building;
    }

    @Override
    public void execute(Game game) {
        game.playSoundEffect("CaptureSE");
        building.healPokemons();
    }
}
