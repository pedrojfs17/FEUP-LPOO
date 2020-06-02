package com.g51.pokemon.controller.states;

import com.g51.pokemon.controller.command.Command;
import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.route.*;
import com.g51.pokemon.controller.command.state.LeaveBuildingCommand;
import com.g51.pokemon.controller.command.state.PauseCommand;
import com.g51.pokemon.model.map.Building;
import com.g51.pokemon.model.position.Position;
import com.g51.pokemon.view.gui.BuildingGui;
import com.g51.pokemon.view.gui.Gui;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BuildingState extends GameState {
    private GameState routeState;
    private BuildingGui gui;
    private Building building;

    public BuildingState(Building building, GameState state) {
        super();
        this.gui = new BuildingGui();
        this.routeState = state;
        this.building = building;
        this.building.cleanObservers();
        this.building.addObserver(this);
        this.building.movePlayerTo(new Position(47, 33));
    }

    public GameState getRouteState() {return this.routeState;}

    @Override
    public void draw(TerminalScreen screen) {
        this.gui.draw(screen, building);
    }

    @Override
    public Command getNextCommand(KeyStroke input) {
        Gui.KEYS com = gui.getKeyPressed(input);
        if(com == Gui.KEYS.ESC) return new PauseCommand();

        if (building.isLeavingBuilding()) {
            building.changeLeaveBuilding();
            if(com == Gui.KEYS.DOWN) return new LeaveBuildingCommand();
        }

        if(com == Gui.KEYS.DOWN) return new MoveDownCommand(building);
        if(com == Gui.KEYS.UP) return new MoveUpCommand(building);
        if(com == Gui.KEYS.LEFT) return new MoveLeftCommand(building);
        if(com == Gui.KEYS.RIGHT) return new MoveRightCommand(building);

        if (com == Gui.KEYS.ENTER && building.isHealingPosition()) return new HealCommand(building);

        return new DoNothingCommand();
    }
}
