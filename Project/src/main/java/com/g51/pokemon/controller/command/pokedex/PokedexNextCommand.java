package com.g51.pokemon.controller.command.pokedex;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.model.pokedex.Pokedex;

public class PokedexNextCommand implements Command {
    private final Pokedex pokedex;

    public PokedexNextCommand(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    @Override
    public void execute(Game game) {
        pokedex.nextPos();
    }
}
