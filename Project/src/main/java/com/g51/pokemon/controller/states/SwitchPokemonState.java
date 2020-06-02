package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.battle.ChoosePokemonCommand;
import com.g51.pokemon.controller.command.menu.NextOptionCommand;
import com.g51.pokemon.controller.command.menu.PreviousOptionCommand;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.menu.Menu;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.view.gui.SwitchPokemonGui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class SwitchPokemonState extends GameState {
    private SwitchPokemonGui gui;
    private BattleState battleState;
    private Battle battle;
    private Menu menu;
    private Player player;

    public SwitchPokemonState(BattleState state, Player player, Battle battle) {
        super();
        this.battle=battle;
        this.gui = new SwitchPokemonGui();
        this.battleState = state;
        this.player = player;
        this.menu = new Menu(player.getTeamSize());
        this.menu.addObserver(this);
    }

    public GameState getPrevState() {return battleState;}

    @Override
    public void draw(TerminalScreen screen) {
        gui.draw(screen, player, menu);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        Gui.KEYS com = gui.getKeyPressed(input);
        if(com == Gui.KEYS.DOWN) return new NextOptionCommand(menu);
        if(com == Gui.KEYS.UP) return new PreviousOptionCommand(menu);
        if(com == Gui.KEYS.ENTER) return new ChoosePokemonCommand(menu.getOption(), battle);
        if(com == Gui.KEYS.ESC) return new ChoosePokemonCommand(0, battle);
        return new DoNothingCommand();
    }
}
