package com.g51.pokemon.model;

import com.g51.pokemon.model.menu.Menu;
import com.g51.pokemon.model.observer.Observer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MenuTests {
    @Test
    public void PauseMenuTest() {
        Observer observer = mock(Observer.class);

        Menu menu = new Menu(5);
        menu.addObserver(observer);

        assertEquals(0, menu.getOption());

        menu.nextOption();
        menu.nextOption();
        assertEquals(2, menu.getOption());

        menu.nextOption();
        menu.nextOption();
        menu.nextOption();
        assertEquals(0, menu.getOption());

        menu.nextOption();
        menu.prevOption();
        menu.prevOption();
        assertEquals(4, menu.getOption());


    }
}
