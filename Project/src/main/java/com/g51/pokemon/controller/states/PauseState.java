package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.QuitCommand;
import com.g51.pokemon.controller.command.menu.NextOptionCommand;
import com.g51.pokemon.controller.command.menu.PreviousOptionCommand;
import com.g51.pokemon.controller.command.state.*;
import com.g51.pokemon.model.menu.Menu;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.view.gui.PauseGui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class PauseState extends GameState {
    private PauseGui gui;
    private GameState prevState;
    private Menu menu;
    private TerminalScreen screen;
    public PauseState(GameState state) {
        super();
        this.gui = new PauseGui();
        this.prevState = state;
        this.menu = new Menu(6);
        this.menu.addObserver(this);
    }

    public GameState getPrevState() {return prevState;}

    @Override
    public void draw(TerminalScreen screen) {
        this.screen=screen;
        prevState.draw(screen);
        gui.draw(screen, menu);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        Gui.KEYS com = gui.getKeyPressed(input);

        if (com == Gui.KEYS.ENTER) {
            switch (menu.getOption()) {
                case 0:
                    return new PokedexCommand();
                case 1:
                    return new TeamCommand();
                case 2:
                    return new BagCommand();
                case 3:
                    return new StoreCommand();
                case 4:
                    return new SaveCommand();
                case 5:
                    return new QuitCommand(screen);
                default:
                    break;
            }
        }
        
        if(com == Gui.KEYS.ESC) return new PauseCommand();

        if(com == Gui.KEYS.DOWN) return new NextOptionCommand(menu);
        if(com == Gui.KEYS.UP) return new PreviousOptionCommand(menu);

        return new DoNothingCommand();

    }
}
