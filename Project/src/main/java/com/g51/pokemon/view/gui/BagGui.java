package com.g51.pokemon.view.gui;

import com.g51.pokemon.model.map.Player;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BagGui {
    private TerminalScreen screen;
    private Player player;

    public void draw(TerminalScreen screen, Player player) {
        this.screen = screen;
        this.player=player;
        drawMenuBack();
        drawBox();
        drawHeaders();
        drawPokeballs();
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if (input.getKeyType() == KeyType.Escape) return Gui.KEYS.ESC;
        return Gui.KEYS.NOTHING;
    }

    private void drawPokeballs() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.putString(10, 13, "Pokeballs");
        graphics.putString(77, 13, String.valueOf(player.getPokeballs()));
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

    private void drawBox() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
        graphics.fillRectangle(new TerminalPosition(6, 12), new TerminalSize(88, 3), ' ');
    }

    private void drawHeaders() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(10,5,"BAG");
        graphics.putString(10,10,"Name");
        graphics.putString(77,10,"Quantity");
    }
}
