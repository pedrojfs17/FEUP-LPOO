package com.g51.pokemon.view.gui;

import com.g51.pokemon.model.menu.Menu;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

public class StartGui {
    private TerminalScreen screen;
    private Menu menu;
    
    public void draw(TerminalScreen screen, Menu menu) {
        this.screen = screen;
        this.menu=menu;
        drawMenuBack();
        drawHeaders();
        drawArrow();
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if (input.getKeyType() == KeyType.ArrowDown) return Gui.KEYS.DOWN;
        if (input.getKeyType() == KeyType.ArrowUp) return Gui.KEYS.UP;

        if (input.getKeyType() == KeyType.Enter) return Gui.KEYS.ENTER;

        return Gui.KEYS.NOTHING;
    }

    private void drawMenuBack() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 20), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.fillRectangle(new TerminalPosition(0, 20), new TerminalSize(100, 20), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 19), new TerminalSize(100, 1), ' ');
        graphics.fillRectangle(new TerminalPosition(44, 17), new TerminalSize(10, 5), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.fillRectangle(new TerminalPosition(46, 18), new TerminalSize(6, 3), ' ');
    }

    private void drawHeaders() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(45,25,"START GAME");
        graphics.putString(45,27,"LOAD GAME");
        graphics.putString(45,29,"INSTRUCTIONS");
        graphics.putString(45,31,"QUIT GAME");
    }

    private void drawArrow() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.setCharacter(43, (25 + 2 * menu.getOption()), Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK);
    }
}
