package com.g51.pokemon.controller.commandtests;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.DoNothingCommand;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class DoNothingCommandsTests {
    @Test
    public void DoNothingCommandTest() {
        DoNothingCommand command = new DoNothingCommand();
        command.execute(mock(Game.class));
    }
}
