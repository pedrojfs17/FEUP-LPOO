package com.g51.pokemon.controller.command.battle;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.controller.command.Command;

public class CatchPokemonCommand implements Command {
    private final Battle battle;

    public CatchPokemonCommand(Battle battle){
        this.battle=battle;
    }

    @Override
    public void execute(Game game) {
        if (this.battle.catchPokemon())
            game.playSoundEffect("CaptureSE");
        else
            game.playSoundEffect("CaptureFailedSE");
    }
}
