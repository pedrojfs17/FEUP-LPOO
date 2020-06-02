package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.state.BackCommand;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.view.gui.BagGui;
import com.g51.pokemon.view.gui.Gui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BagState extends MenuState {
    private Player player;
    private BagGui gui;

    public BagState(PauseState state, Player player) {
        super(state);
        this.player = player;
        this.gui = new BagGui();
    }

    @Override
    public void draw(TerminalScreen screen) {
        gui.draw(screen,player);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        if (gui.getKeyPressed(input)== Gui.KEYS.ESC) return new BackCommand();
        else return new DoNothingCommand();
    }
}
