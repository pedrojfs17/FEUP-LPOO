package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.BattleState;
import com.g51.pokemon.controller.states.GameState;

public class BeginBattleCommand implements Command {
    @Override
    public void execute(Game game) {
        game.changeBackgroundMusic("Battle");

        GameState state = game.getState();
        BattleState newState = new BattleState(game.newBattle(), state);
        newState.addObserver(game);
        game.setState(newState);
        game.changed();
    }
}
