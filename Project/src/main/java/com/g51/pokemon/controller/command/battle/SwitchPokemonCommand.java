package com.g51.pokemon.controller.command.battle;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.BattleState;
import com.g51.pokemon.controller.states.SwitchPokemonState;
import com.g51.pokemon.model.battle.Battle;

public class SwitchPokemonCommand implements Command {
    private Battle battle;

    public SwitchPokemonCommand(Battle battle) {this.battle = battle;}

    @Override
    public void execute(Game game) {
        BattleState state = (BattleState)game.getState();
        SwitchPokemonState newState = new SwitchPokemonState(state, game.getPlayer(), battle);
        newState.addObserver(game);
        game.setState(newState);
        game.changed();
    }
}
