package com.g51.pokemon.model;

import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.observer.Observer;
import com.g51.pokemon.model.store.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StoreTests {
    @Test
    public void StoreTest(){
        Player player = mock(Player.class);
        Observer observer = mock(Observer.class);
        when(player.getMoney()).thenReturn(100);
        when(player.getPokeballs()).thenReturn(11).thenReturn(12).thenReturn(12).thenReturn(11).thenReturn(0);

        Store store = new Store(player);
        store.addObserver(observer);

        assertEquals(100, store.getPlayerMoney());
        assertEquals("Money: 100", store.getMessage());

        store.buy(10);
        verify(player,times(1)).subtractMoney(10);
        verify(player,times(1)).addPokeball();
        verify(observer,times(1)).changed();
        assertEquals("You bought a Pokeball! Current Pokeballs: 11", store.getMessage());

        store.buy(100);
        verify(player,times(1)).subtractMoney(100);
        verify(player,times(2)).addPokeball();
        verify(observer,times(2)).changed();
        assertEquals("You bought a Pokeball! Current Pokeballs: 12", store.getMessage());

        store.sell(10);
        verify(player,times(1)).addMoney(10);
        verify(player,times(1)).subPokeball();
        verify(observer,times(3)).changed();
        assertEquals("You sold a pokeball! Current pokeballs: 11", store.getMessage());

        store.buy(1000);
        assertEquals("You don't have enough money!", store.getMessage());

        store.sell(10);
        assertEquals("You don't have pokeballs!", store.getMessage());
    }
}
