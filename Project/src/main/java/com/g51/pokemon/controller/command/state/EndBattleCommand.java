package com.g51.pokemon.controller.command.state;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.BattleState;
import com.g51.pokemon.controller.states.GameState;
import com.g51.pokemon.controller.states.RouteState;
import com.g51.pokemon.model.battle.Battle;

public class EndBattleCommand implements Command {
    Battle.STATE result;

    public EndBattleCommand(Battle.STATE result) {this.result = result;}

    @Override
    public void execute(Game game) {
        game.changeBackgroundMusic("Route");

        BattleState state = (BattleState)game.getState();
        GameState previousState = state.getPrevState();
        if (result == Battle.STATE.LOSE)
            ((RouteState) previousState).leftBuilding();
        game.setState(state.getPrevState());
        game.changed();
    }
}
