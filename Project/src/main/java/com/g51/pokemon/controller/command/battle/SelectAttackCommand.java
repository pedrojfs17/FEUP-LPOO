package com.g51.pokemon.controller.command.battle;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.controller.command.Command;

public class SelectAttackCommand implements Command {
    private final Battle battle;
    private final int attack;

    public SelectAttackCommand(Battle battle, int attack){
        this.battle=battle;
        this.attack = attack;
    }
    @Override
    public void execute(Game game) {
        this.battle.continueBattle(attack);
    }
}
