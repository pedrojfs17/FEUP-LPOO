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

public class PauseGui {
    private TerminalScreen screen;
    private Menu menu;

    public void draw(TerminalScreen screen, Menu menu) {
        this.screen = screen;
        this.menu = menu;
        drawMenuBack();
        drawTexts();
        drawArrow();
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if (input.getKeyType() == KeyType.Escape) return Gui.KEYS.ESC;

        if (input.getKeyType() == KeyType.ArrowDown) return Gui.KEYS.DOWN;
        if (input.getKeyType() == KeyType.ArrowUp) return Gui.KEYS.UP;

        if (input.getKeyType() == KeyType.Enter) return Gui.KEYS.ENTER;

        return Gui.KEYS.NOTHING;
    }

    private void drawMenuBack() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(20, 15), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(2, 1), new TerminalSize(16, 13), ' ');
    }

    private void drawTexts() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.putString(7,2,"Pokedex");
        graphics.putString(7,4,"Team");
        graphics.putString(7,6,"Bag");
        graphics.putString(7,8,"Store");
        graphics.putString(7,10,"Save");
        graphics.putString(7,12,"Quit");
    }

    private void drawArrow() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.setCharacter(4, (2 + 2 * menu.getOption()), Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK);
    }
}
