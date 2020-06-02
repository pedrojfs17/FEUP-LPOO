package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.pokedex.PokedexNextCommand;
import com.g51.pokemon.controller.command.pokedex.PokedexPreviousCommand;
import com.g51.pokemon.controller.command.state.BackCommand;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.view.gui.PokedexGui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class PokedexState extends MenuState {
    private Pokedex pokedex;
    private PokedexGui gui;

    public PokedexState(PauseState state, Pokedex pokedex) {
        super(state);
        this.pokedex = pokedex;
        this.pokedex.addObserver(this);
        this.gui = new PokedexGui();
    }

    @Override
    public void draw(TerminalScreen screen) {
        gui.draw(screen, pokedex);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        Gui.KEYS com = gui.getKeyPressed(input);
        if(com == Gui.KEYS.ESC) return new BackCommand();
        if(com == Gui.KEYS.DOWN) return new PokedexNextCommand(pokedex);
        if(com == Gui.KEYS.UP) return new PokedexPreviousCommand(pokedex);
        return new DoNothingCommand();
    }
}
