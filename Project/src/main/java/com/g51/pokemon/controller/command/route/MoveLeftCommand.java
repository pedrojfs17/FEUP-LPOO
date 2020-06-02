package com.g51.pokemon.controller.command.route;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.map.Movable;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.model.position.Position;

public class MoveLeftCommand implements Command {
    private final Movable map;

    public MoveLeftCommand(Movable map) {
        this.map = map;
    }

    @Override
    public void execute(Game game) {
        Position position = map.getPlayerPosition().left();
        if (!map.movePlayerTo(position))
            game.playSoundEffect("CollisionSE");
    }
}
