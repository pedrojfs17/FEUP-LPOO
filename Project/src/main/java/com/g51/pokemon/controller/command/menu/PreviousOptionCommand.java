package com.g51.pokemon.controller.command.menu;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.model.menu.Menu;

public class PreviousOptionCommand implements Command {
    private Menu menu;

    public PreviousOptionCommand(Menu menu) {this.menu = menu;}

    @Override
    public void execute(Game game) {
        game.playSoundEffect("MenuOptionSE");
        menu.prevOption();
    }
}
