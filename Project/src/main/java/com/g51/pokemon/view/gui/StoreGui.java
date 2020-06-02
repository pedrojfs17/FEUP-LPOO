package com.g51.pokemon.view.gui;

import com.g51.pokemon.model.store.Store;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

public class StoreGui {
    private TerminalScreen screen;
    private Store store;
    
    public void draw(TerminalScreen screen,Store store) {
        this.screen = screen;
        this.store=store;
        drawMenuBack();
        drawBoxes();
        drawHeaders();
        drawItems();
        drawArrow();
        drawLog();
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if (input.getKeyType() == KeyType.Escape) return Gui.KEYS.ESC;

        if (input.getKeyType() == KeyType.Enter) return Gui.KEYS.ENTER;
        if (input.getKeyType() == KeyType.Character && (input.getCharacter() == 's' || input.getCharacter() == 'S')) return Gui.KEYS.S;

        return Gui.KEYS.NOTHING;
    }

    private void drawItems() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(10, 13, "Pokeball");
        graphics.putString(87, 13, "300$");
    }

    private void drawMenuBack() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 20), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.fillRectangle(new TerminalPosition(0, 20), new TerminalSize(100, 20), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 19), new TerminalSize(100, 1), ' ');
    }

    private void drawBoxes() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
        graphics.fillRectangle(new TerminalPosition(6, 12 ), new TerminalSize(88, 3), ' ');

    }

    private void drawHeaders() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(10,10,"Name");
        graphics.putString(87,10,"Price");
    }

    private void drawArrow() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.setCharacter(6, 13, Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK);
    }

    private void drawLog() {
        TextGraphics graphics = screen.newTextGraphics();
        String message = store.getMessage();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#787878"));
        graphics.fillRectangle(new TerminalPosition(10, 2), new TerminalSize(80, 7), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(12, 3), new TerminalSize(76, 5), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#0E0E0C"));
        graphics.putString(14,4,"Store");
        graphics.putString(70,4,"Money: " + store.getPlayerMoney() + "$");
        graphics.putString(14,6,message);
    }
}
