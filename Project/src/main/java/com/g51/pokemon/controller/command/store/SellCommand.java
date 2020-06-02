package com.g51.pokemon.controller.command.store;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.store.Store;
import com.g51.pokemon.controller.command.Command;

public class SellCommand implements Command {
    private Store store;

    public SellCommand(Store store){this.store=store;}

    @Override
    public void execute(Game game) {
        game.playSoundEffect("PurchaseSE");
        this.store.sell(300);
    }
}
