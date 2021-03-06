package com.seixas.hero.game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.seixas.hero.game.Arena;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game() {
        arena = new Arena(80, 24, 1);
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        int level = 1;
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            arena.verifyMonsterCollisions();
            if (!arena.checkHeroEnergy()) {
                gameOver();
                screen.close();
                break;
            }
            if (arena.finishedLevel()) {
                level++;
                arena = new Arena(80, 24, level);
            }
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }

    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            try {
                screen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        arena.processKey(key);
    }

    private void gameOver() throws IOException {
        screen.clear();
        screen.newTextGraphics().setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.newTextGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');
        // Game Over Text
        screen.newTextGraphics().setForegroundColor(TextColor.Factory.fromString("#000000"));
        screen.newTextGraphics().enableModifiers(SGR.BOLD);
        screen.newTextGraphics().putString(new TerminalPosition(36, 11), "GAME OVER");
        screen.refresh();

        while (true) {
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.EOF || key.getKeyType() == KeyType.Enter)
                break;
        }
    }

}
