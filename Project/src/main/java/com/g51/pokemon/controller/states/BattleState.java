package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.battle.*;
import com.g51.pokemon.controller.command.state.EndBattleCommand;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.view.gui.BattleGui;
import com.g51.pokemon.view.gui.Gui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BattleState extends GameState {
    private GameState routeState;
    private BattleGui gui;
    private Battle battle;

    public BattleState(Battle battle, GameState state) {
        super();
        this.gui = new BattleGui();
        this.routeState = state;
        this.battle=battle;
        this.battle.addObserver(this);
    }

    public GameState getPrevState() {return routeState;}

    @Override
    public void draw(TerminalScreen screen) {
        gui.draw(screen,battle);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        if (battle.endedBattle()) return new EndBattleCommand(battle.getState());

        if (!battle.getTurn()) return new OpponentTurnCommand(battle);
        
        Gui.KEYS com = gui.getKeyPressed(input);

        if(com == Gui.KEYS.FIRST) return new SelectAttackCommand(battle,0);
        if(com == Gui.KEYS.SECOND) return new SelectAttackCommand(battle,1);
        if(com == Gui.KEYS.THIRD) return new SelectAttackCommand(battle,2);
        if(com == Gui.KEYS.FOURTH) return new SelectAttackCommand(battle,3);

        if(com == Gui.KEYS.C) return new CatchPokemonCommand(battle);
        if(com == Gui.KEYS.R) return new RunCommand(battle);
        if(com == Gui.KEYS.S) return new SwitchPokemonCommand(battle);

        return new DoNothingCommand();
    }
}
