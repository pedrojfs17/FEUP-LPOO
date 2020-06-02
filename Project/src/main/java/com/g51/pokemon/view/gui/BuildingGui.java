package com.g51.pokemon.view.gui;

import com.g51.pokemon.model.map.*;
import com.g51.pokemon.model.position.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BuildingGui {
    private TerminalScreen screen;
    private Building building;

    public void draw(TerminalScreen screen, Building building) {
        this.screen = screen;
        this.building = building;
        drawBackground();
        for (Element element : building.getAllElements()) drawElement(element);
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if (input.getKeyType() == KeyType.Escape) return Gui.KEYS.ESC;
        if (input.getKeyType() == KeyType.Enter) return Gui.KEYS.ENTER;

        if (input.getKeyType() == KeyType.ArrowDown) return Gui.KEYS.DOWN;
        if (input.getKeyType() == KeyType.ArrowUp) return Gui.KEYS.UP;
        if (input.getKeyType() == KeyType.ArrowLeft) return Gui.KEYS.LEFT;
        if (input.getKeyType() == KeyType.ArrowRight) return Gui.KEYS.RIGHT;

        return Gui.KEYS.NOTHING;
    }

    private void drawBackground() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffcc66"));
        graphics.fillRectangle(new TerminalPosition(10, 5), new TerminalSize(80, 30), ' ');
    }

    private void drawElement(Element element) {
        if (element instanceof Player) drawCharacter(element.getPosition(), "X", "#FFFFFF");
        else if (element instanceof Wall) drawBack(element.getPosition(), "#996600");
        else if (element instanceof BuildingDoor) drawBack(element.getPosition(), "#663300");
        else if (element instanceof Heal) drawBack(element.getPosition(), "#FF0000");
    }

    private void drawCharacter(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        if (building.isHealingPosition())
            graphics.setBackgroundColor(TextColor.Factory.fromString("#00FF00"));
        else
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ffcc66"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(position.getX(), position.getY(), character);
    }

    private void drawBack(Position position, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(position.getX(), position.getY(), " ");
    }
}

