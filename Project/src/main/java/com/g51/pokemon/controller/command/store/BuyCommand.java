package com.g51.pokemon.controller.command.store;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.store.Store;
import com.g51.pokemon.controller.command.Command;

public class BuyCommand implements Command {
    private Store store;

    public BuyCommand(Store store){this.store=store;}

    @Override
    public void execute(Game game) {
        game.playSoundEffect("PurchaseSE");
        this.store.buy(300);
    }
}
