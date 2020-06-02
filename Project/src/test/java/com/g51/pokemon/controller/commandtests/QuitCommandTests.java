package com.g51.pokemon.controller.commandtests;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.QuitCommand;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class QuitCommandTests {
    @Test
    public void QuitCommandTest() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        QuitCommand command = new QuitCommand(screen);

        Game game = mock(Game.class);

        command.execute(game);

        verify(screen,times(1)).close();
        verify(game, times(1)).playSoundEffect("PauseSE");
    }
}
