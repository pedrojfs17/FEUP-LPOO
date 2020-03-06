package com.seixas.hero.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.seixas.hero.game.Position;

public class Hero extends Element {
    private int energy;

    public Hero(int x, int y) {
        super(x, y);
        this.energy = 3;
    }

    public void draw(TextGraphics graphics) {
        switch(energy) {
            case 1:
                graphics.setForegroundColor(TextColor.Factory.fromString("#FF3300"));
                break;
            case 2:
                graphics.setForegroundColor(TextColor.Factory.fromString("#FF9900"));
                break;
            case 3:
                graphics.setForegroundColor(TextColor.Factory.fromString("#99FF33"));
                break;
            default:
                break;
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "P");
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
