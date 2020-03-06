package com.seixas.hero.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.seixas.hero.game.Position;

import java.util.Random;

public class Monster extends Element {
    public Monster(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

    public Position move() {
        Position p = new Position(position.getX(), position.getY());

        Random random = new Random();
        int num = random.nextInt(8) + 1;

        switch (num) {
            case 1: // Up Left
                p.setY(p.getY() - 1);
                p.setX(p.getX() - 1);
                break;
            case 2: // Up
                p.setY(p.getY() - 1);
                break;
            case 3: // Up Right
                p.setY(p.getY() - 1);
                p.setX(p.getX() + 1);
                break;
            case 4: // Right
                p.setX(p.getX() + 1);
                break;
            case 5: // Down Right
                p.setY(p.getY() + 1);
                p.setX(p.getX() + 1);
                break;
            case 6: // Down
                p.setY(p.getY() + 1);
                break;
            case 7: // Down Left
                p.setY(p.getY() + 1);
                p.setX(p.getX() - 1);
                break;
            case 8: // Left
                p.setX(p.getX() - 1);
                break;
            default:
                break;
        }

        return p;
    }
}
