package com.g51.pokemon.controller.command;

import com.g51.pokemon.controller.Game;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class QuitCommand implements Command {
    private final Screen screen;

    public QuitCommand(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void execute(Game game) {
        game.playSoundEffect("PauseSE");
        try {
            screen.close();
        } catch (IOException ignored) {}
    }
}
