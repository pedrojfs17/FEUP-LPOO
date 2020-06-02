package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class MenuState extends GameState {
    private PauseState state;

    public MenuState(PauseState state){
        this.state=state;
    }

    public PauseState getPrev() {
        return state;
    }

    @Override
    public void draw(TerminalScreen screen) {}

    @Override
    public Command getNextCommand(KeyStroke input) {
        return null;
    }
}
