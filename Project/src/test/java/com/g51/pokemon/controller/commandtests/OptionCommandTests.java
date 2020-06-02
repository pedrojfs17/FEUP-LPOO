package com.g51.pokemon.controller.commandtests;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.menu.NextOptionCommand;
import com.g51.pokemon.controller.command.menu.PreviousOptionCommand;
import com.g51.pokemon.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OptionCommandTests {
    private Menu menu;
    private Game game;
    @BeforeEach
    public void prepare(){
        this.menu =new Menu(5);
        game=mock(Game.class);
    }
    @Test
    public void NextOptionCommandTest() {
        NextOptionCommand command = new NextOptionCommand(this.menu);
        command.execute(game);
        verify(game, times(1)).playSoundEffect("MenuOptionSE");

        assertEquals(1,this.menu.getOption());
    }

    @Test
    public void PreviousOptionCommandTest() {
        PreviousOptionCommand command = new PreviousOptionCommand(this.menu);
        command.execute(game);
        verify(game, times(1)).playSoundEffect("MenuOptionSE");

        assertEquals(4,this.menu.getOption());
    }
}
