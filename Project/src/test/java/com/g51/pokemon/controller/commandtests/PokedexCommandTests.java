package com.g51.pokemon.controller.commandtests;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.controller.command.pokedex.PokedexNextCommand;
import com.g51.pokemon.controller.command.pokedex.PokedexPreviousCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class PokedexCommandTests {
    @Test
    public void PokedexCommandTests(){
        Pokedex pokedex = Mockito.mock(Pokedex.class);

        PokedexNextCommand pokedexNextCommand = new PokedexNextCommand(pokedex);
        pokedexNextCommand.execute(mock(Game.class));

        verify(pokedex,times(1)).nextPos();

        PokedexPreviousCommand pokedexPreviousCommand = new PokedexPreviousCommand(pokedex);
        pokedexPreviousCommand.execute(mock(Game.class));

        verify(pokedex,times(1)).previousPos();
    }
}
