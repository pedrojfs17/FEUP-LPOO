package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.route.MoveDownCommand;
import com.g51.pokemon.controller.command.route.MoveLeftCommand;
import com.g51.pokemon.controller.command.route.MoveRightCommand;
import com.g51.pokemon.controller.command.route.MoveUpCommand;
import com.g51.pokemon.controller.command.state.BeginBattleCommand;
import com.g51.pokemon.controller.command.state.EnterBuildingCommand;
import com.g51.pokemon.controller.command.state.PauseCommand;
import com.g51.pokemon.model.map.Route;
import com.g51.pokemon.view.gui.Gui;
import com.g51.pokemon.view.gui.RouteGui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class RouteState extends GameState {
    private RouteGui gui;
    private Route route;

    public RouteState(Route route) {
        super();
        this.gui = new RouteGui();
        this.route = route;
        this.route.movePlayerTo(route.getPlayerPosition());
        this.route.addObserver(this);
    }

    public void leftBuilding() {this.route.movePlayerToBuildingDoor();}

    @Override
    public void draw(TerminalScreen screen) {
        gui.draw(screen, route);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        if(route.isBattleFound()){
            route.changeBattleFound();
            return new BeginBattleCommand();
        }

        Gui.KEYS com = gui.getKeyPressed(input);

        if (route.inBuilding() > -1) {
            if (com == Gui.KEYS.DOWN) this.route.changeInBuilding();
            else return new EnterBuildingCommand();
        }

        if (com == Gui.KEYS.ESC) return new PauseCommand();
        if (com == Gui.KEYS.DOWN) return new MoveDownCommand(route);
        if (com == Gui.KEYS.UP) return new MoveUpCommand(route);
        if (com == Gui.KEYS.LEFT) return new MoveLeftCommand(route);
        if (com == Gui.KEYS.RIGHT) return new MoveRightCommand(route);

        return new DoNothingCommand();
    }
}
