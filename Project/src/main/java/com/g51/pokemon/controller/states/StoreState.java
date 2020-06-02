package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.state.BackCommand;
import com.g51.pokemon.controller.command.store.BuyCommand;
import com.g51.pokemon.controller.command.store.SellCommand;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.store.Store;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.view.gui.StoreGui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class StoreState extends MenuState {
    private Store store;
    private StoreGui gui;

    public StoreState(PauseState state, Player player) {
        super(state);
        this.store = new Store(player);
        this.store.addObserver(this);
        this.gui = new StoreGui();
    }

    @Override
    public void draw(TerminalScreen screen) {
        gui.draw(screen,store);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        Gui.KEYS com = gui.getKeyPressed(input);
        if(com == Gui.KEYS.ESC) return new BackCommand();
        if(com == Gui.KEYS.ENTER) return new BuyCommand(store);
        if(com == Gui.KEYS.S) return new SellCommand(store);
        return new DoNothingCommand();
    }
}
