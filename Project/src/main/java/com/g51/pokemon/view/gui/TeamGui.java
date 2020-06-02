package com.g51.pokemon.view.gui;

import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.List;

public class TeamGui {
    private TerminalScreen screen;
    private Player player;
    
    public void draw(TerminalScreen screen, Player player) {
        this.screen = screen;
        this.player=player;
        drawMenuBack();
        drawBoxes();
        drawHeaders();
        drawPokemons();
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if (input.getKeyType() == KeyType.Escape) return Gui.KEYS.ESC;

        return Gui.KEYS.NOTHING;
    }

    private void drawPokemons() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        List<Pokemon> creatures = player.getTeam();

        for (int i = 0; i < creatures.size(); i++) {
            graphics.putString(10, 13 + i * 4, creatures.get(i).getName());
            graphics.putString(37, 13 + i * 4, String.valueOf(creatures.get(i).getLevel()));
            graphics.putString(47, 13 + i * 4, creatures.get(i).getType().toString());
            graphics.putString(57, 13 + i * 4, String.valueOf(creatures.get(i).getHealth()));
            graphics.putString(67, 13 + i * 4, String.valueOf(creatures.get(i).getAtk()));
            graphics.putString(77, 13 + i * 4, String.valueOf(creatures.get(i).getDef()));
            graphics.putString(87, 13 + i * 4, String.valueOf(creatures.get(i).getSpeed()));
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

        for (int i = 0; i < 6; i++) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
            graphics.fillRectangle(new TerminalPosition(6, 12 + i * 4), new TerminalSize(88, 3), ' ');
        }
    }

    private void drawHeaders() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(10,5,"TEAM");
        graphics.putString(10,10,"Name");
        graphics.putString(37,10,"Level");
        graphics.putString(47,10,"Type");
        graphics.putString(57,10,"Health");
        graphics.putString(67,10,"Attack");
        graphics.putString(77,10,"Defense");
        graphics.putString(87,10,"Speed");
    }
}
