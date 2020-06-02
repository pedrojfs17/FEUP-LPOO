package com.g51.pokemon.view.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

public class InstructionsGui {
    private TerminalScreen screen;

    public void draw(TerminalScreen screen) {
        this.screen = screen;
        drawInstructionsBack();
        drawBox();
        drawText();
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if (input.getKeyType() == KeyType.Escape) return Gui.KEYS.ESC;
        return Gui.KEYS.NOTHING;
    }

    private void drawInstructionsBack() {
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

    private void drawBox(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#787878"));
        graphics.fillRectangle(new TerminalPosition(4, 2), new TerminalSize(92, 36), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(6, 3), new TerminalSize(88, 34), ' ');
    }

    private void drawText() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(8,4,"MOVE AROUND WITH THE ARROW KEYS;");
        graphics.putString(8,6,"YOU START OFF WITH ONE POKEMON, ONE POKEBALL AND NO MONEY;");
        graphics.putString(8,8,"EARN MONEY BY DEFEATING OR CATCHING POKEMONS;");
        graphics.putString(8,10,"YOU HAVE A CHANCE OF FINDING POKEMONS WITHIN THE TALL GRASS,");
        graphics.putString(8,11,"LOOKOUT FOR DARK GREEN SPOTS IN THE MAP;");
        graphics.putString(8,13,"WHEN IN BATTLE USE 1-4 TO USE AN ATTACK, 'c/C' TO TRY TO CATCH A POKEMON");
        graphics.putString(8,14,", 'r/R' TO TRY TO RUN AND 's/S' TO CHANGE POKEMON;");
        graphics.putString(8,16,"THERE ARE ALSO BUILDINGS IN THE MAP, THAT YOU CAN ENTER");
        graphics.putString(8,17,"AND HEAL YOUR POKEMONS INSIDE;");
        graphics.putString(8,19,"IN THE PAUSE MENU YOU CAN: SEE THE POKEDEX, VIEW YOUR TEAM, CHECK THE STORE");
        graphics.putString(8,20,"SAVE THE GAME AND QUIT IT;");
        graphics.putString(8,35,"PRESS ESC TO RETURN TO THE MAIN MENU...");
    }
}
