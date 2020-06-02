package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.state.BackCommand;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.view.gui.TeamGui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class TeamState extends MenuState{
    private Player player;
    private TeamGui gui;

    public TeamState(PauseState state, Player player) {
        super(state);
        this.player = player;
        this.gui = new TeamGui();
    }

    @Override
    public void draw(TerminalScreen screen) {
        gui.draw(screen,player);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        if (gui.getKeyPressed(input) == Gui.KEYS.ESC) return new BackCommand();
        return new DoNothingCommand();
    }
}
