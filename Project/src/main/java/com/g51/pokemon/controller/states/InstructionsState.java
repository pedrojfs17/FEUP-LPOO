package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.state.MainMenuCommand;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.view.gui.InstructionsGui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class InstructionsState extends GameState {
    InstructionsGui gui;
    StartState startState;

    public InstructionsState(StartState startState) {
        super();
        this.startState=startState;
        this.gui = new InstructionsGui();
    }

    public StartState getPrev() {
        return startState;
    }

    @Override
    public void draw(TerminalScreen screen) {
        gui.draw(screen);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        if (gui.getKeyPressed(input) == Gui.KEYS.ESC) return new MainMenuCommand();
        return new DoNothingCommand();
    }
}
