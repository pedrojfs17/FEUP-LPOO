package com.g51.pokemon.controller.command.battle;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.controller.command.Command;

public class RunCommand implements Command {
    private final Battle battle;

    public RunCommand(Battle battle){
        this.battle=battle;
    }

    @Override
    public void execute(Game game) {
        game.playSoundEffect("RunAwaySE");
        this.battle.runBattle();
    }
}
