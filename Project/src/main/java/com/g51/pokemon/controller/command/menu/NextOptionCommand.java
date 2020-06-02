package com.g51.pokemon.controller.command.menu;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.menu.Menu;
import com.g51.pokemon.controller.command.Command;

public class NextOptionCommand implements Command {
    private Menu menu;

    public NextOptionCommand(Menu menu) {this.menu = menu;}

    @Override
    public void execute(Game game) {
        game.playSoundEffect("MenuOptionSE");
        menu.nextOption();
    }
}
