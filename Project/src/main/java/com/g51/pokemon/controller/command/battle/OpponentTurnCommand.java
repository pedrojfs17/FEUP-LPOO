package com.g51.pokemon.controller.command.battle;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.controller.command.Command;

import java.util.Random;

public class OpponentTurnCommand implements Command {
    private final Battle battle;

    public OpponentTurnCommand(Battle battle){
        this.battle=battle;
    }

    @Override
    public void execute(Game game) {
        Random random = new Random();
        this.battle.continueBattle(random.nextInt(4));
    }
}
