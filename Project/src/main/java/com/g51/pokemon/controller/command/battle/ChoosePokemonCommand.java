package com.g51.pokemon.controller.command.battle;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.states.SwitchPokemonState;
import com.g51.pokemon.model.battle.Battle;

public class ChoosePokemonCommand implements Command {
    private int pokemonIndex;
    private Battle battle;

    public ChoosePokemonCommand(int pokemonIndex, Battle battle) {
        this.pokemonIndex = pokemonIndex;
        this.battle = battle;
    }

    @Override
    public void execute(Game game) {
        game.playSoundEffect("CaptureFailedSE");

        SwitchPokemonState state = (SwitchPokemonState)game.getState();
        battle.switchPokemon(pokemonIndex);
        game.setState(state.getPrevState());
        game.changed();
    }
}
