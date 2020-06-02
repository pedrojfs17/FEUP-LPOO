package com.g51.pokemon.controller.commandtests;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.store.Store;
import com.g51.pokemon.controller.command.store.BuyCommand;
import com.g51.pokemon.controller.command.store.SellCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public class StoreTests {
    private Store store;
    private Game game;

    @BeforeEach
    public void createStore(){
        game = mock(Game.class);
        store = mock(Store.class);
    }

    @Test
    public void BuyCommand(){
        BuyCommand buyCommand = new BuyCommand(store);
        buyCommand.execute(game);
        verify(store,times(1)).buy(300);
        verify(game, times(1)).playSoundEffect("PurchaseSE");
    }

    @Test
    public void SellCommand(){
        SellCommand sellCommand = new SellCommand(store);
        sellCommand.execute(game);
        verify(store,times(1)).sell(300);
        verify(game, times(1)).playSoundEffect("PurchaseSE");
    }
}
