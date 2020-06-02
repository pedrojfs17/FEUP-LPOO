package com.g51.pokemon.view.gui;

import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.List;

public class PokedexGui {
    private TerminalScreen screen;
    private Pokedex pokedex;

    public void draw(TerminalScreen screen, Pokedex pokedex) {
        this.pokedex = pokedex;
        this.screen = screen;
        drawMenuBack();
        drawBoxes();
        drawHeaders();
        drawPokemons();
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if (input.getKeyType() == KeyType.Escape) return Gui.KEYS.ESC;

        if (input.getKeyType() == KeyType.ArrowDown) return Gui.KEYS.DOWN;
        if (input.getKeyType() == KeyType.ArrowUp) return Gui.KEYS.UP;

        return Gui.KEYS.NOTHING;
    }

    private void drawPokemons() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        List<Pokemon> creatures = pokedex.getCreatures();

        for (int i = 0; i < creatures.size(); i++) {
            graphics.putString(10, 7 + i * 4, creatures.get(i).getName());
            graphics.putString(42, 7 + i * 4, creatures.get(i).getType().toString());
            graphics.putString(57, 7 + i * 4, String.valueOf(creatures.get(i).getHealth()));
            graphics.putString(67, 7 + i * 4, String.valueOf(creatures.get(i).getAtk()));
            graphics.putString(77, 7 + i * 4, String.valueOf(creatures.get(i).getDef()));
            graphics.putString(87, 7 + i * 4, String.valueOf(creatures.get(i).getSpeed()));
        }
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

        graphics.setBackgroundColor(TextColor.Factory.fromString("#990000"));

        graphics.fillRectangle(new TerminalPosition(8, 2), new TerminalSize(30, 3), ' ');
        graphics.fillRectangle(new TerminalPosition(40, 2), new TerminalSize(13, 3), ' ');
        graphics.fillRectangle(new TerminalPosition(55, 2), new TerminalSize(8, 3), ' ');
        graphics.fillRectangle(new TerminalPosition(65, 2), new TerminalSize(8, 3), ' ');
        graphics.fillRectangle(new TerminalPosition(75, 2), new TerminalSize(9, 3), ' ');
        graphics.fillRectangle(new TerminalPosition(86, 2), new TerminalSize(7, 3), ' ');

        for (int i = 0; i < 8; i++) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
            graphics.fillRectangle(new TerminalPosition(6, 6 + i * 4), new TerminalSize(88, 3), ' ');
        }
    }

    private void drawHeaders() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#990000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(20,3,"Name");
        graphics.putString(45,3,"Type");
        graphics.putString(56,3,"Health");
        graphics.putString(66,3,"Attack");
        graphics.putString(76,3,"Defense");
        graphics.putString(87,3,"Speed");
    }
}
