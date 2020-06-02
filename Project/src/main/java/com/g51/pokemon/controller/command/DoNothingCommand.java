package com.g51.pokemon.controller.command;

import com.g51.pokemon.controller.Game;

public class DoNothingCommand implements Command {
    @Override
    public void execute(Game game) {}
}
