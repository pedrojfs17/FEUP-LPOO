package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.QuitCommand;
import com.g51.pokemon.controller.command.menu.NextOptionCommand;
import com.g51.pokemon.controller.command.menu.PreviousOptionCommand;
import com.g51.pokemon.controller.command.state.InstructionsCommand;
import com.g51.pokemon.controller.command.state.LoadCommand;
import com.g51.pokemon.controller.command.state.StartCommand;
import com.g51.pokemon.model.menu.Menu;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.view.gui.StartGui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class StartState extends GameState {
    private StartGui gui;
    private Menu menu;
    private TerminalScreen screen;

    public StartState() {
        super();
        this.menu = new Menu(4);
        this.menu.addObserver(this);
        this.gui = new StartGui();
    }

    @Override
    public void draw(TerminalScreen screen) {
        this.screen=screen;
        gui.draw(screen,this.menu);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        Gui.KEYS com = gui.getKeyPressed(input);

        if(com == Gui.KEYS.DOWN) return new NextOptionCommand(menu);
        if(com == Gui.KEYS.UP) return new PreviousOptionCommand(menu);


        if(com == Gui.KEYS.ENTER) {
            switch (menu.getOption()) {
                case 0:
                    return new StartCommand();
                case 1:
                    return new LoadCommand();
                case 2:
                    return new InstructionsCommand();
                case 3:
                    return new QuitCommand(screen);
                default:
                    break;
            }
        }

        return new DoNothingCommand();
    }
}
