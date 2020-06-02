package com.g51.pokemon.model.map;

import com.g51.pokemon.model.position.Position;
import java.io.Serializable;
import java.util.List;

public abstract class Movable implements Serializable {
    Player player;

    public abstract boolean movePlayerTo(Position position);

    public abstract boolean canMove(Position position);

    public Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;

        return null;
    }

    public Position getPlayerPosition() {return player.getPosition();}

    public abstract void addElement(Element element);
}
