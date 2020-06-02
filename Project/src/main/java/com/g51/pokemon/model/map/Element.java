package com.g51.pokemon.model.map;

import com.g51.pokemon.model.position.Position;
import java.io.Serializable;

public abstract class Element implements Serializable {
    private Position position;

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
