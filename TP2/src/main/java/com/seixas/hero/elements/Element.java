package com.seixas.hero.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.seixas.hero.game.Position;

public abstract class Element {
    protected Position position;

    public Element(int x, int y) {
        position = new Position(x, y);
    }

    abstract void draw(TextGraphics graphics);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
