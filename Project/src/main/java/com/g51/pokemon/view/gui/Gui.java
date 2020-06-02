package com.g51.pokemon.view.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.g51.pokemon.controller.command.*;
import com.g51.pokemon.controller.states.GameState;

import java.io.IOException;

public class Gui {
    private final TerminalScreen screen;

    private boolean isFinished;

    public enum KEYS {UP, DOWN, LEFT, RIGHT, ESC, ENTER, R, S, C, FIRST, SECOND, THIRD, FOURTH, NOTHING}

    public Gui() throws IOException {
        TerminalSize terminalSize = new TerminalSize(100,40);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        this.isFinished = false;
    }

    public void draw(GameState state) throws IOException {
        screen.clear();
        state.draw(screen);
        screen.refresh();
    }


    public Command getNextCommand(GameState state) throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.EOF) {
            this.isFinished = true;
            return new QuitCommand(screen);
        }

        return state.getNextCommand(input);
    }

    public boolean isFinished() {return this.isFinished;}
}
