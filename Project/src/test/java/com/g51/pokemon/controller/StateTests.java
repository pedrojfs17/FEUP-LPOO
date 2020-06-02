package com.g51.pokemon.controller;

import com.g51.pokemon.controller.command.DoNothingCommand;
import com.g51.pokemon.controller.command.QuitCommand;
import com.g51.pokemon.controller.command.battle.*;
import com.g51.pokemon.controller.command.menu.NextOptionCommand;
import com.g51.pokemon.controller.command.menu.PreviousOptionCommand;
import com.g51.pokemon.controller.command.pokedex.PokedexNextCommand;
import com.g51.pokemon.controller.command.pokedex.PokedexPreviousCommand;
import com.g51.pokemon.controller.command.route.*;
import com.g51.pokemon.controller.command.state.*;
import com.g51.pokemon.controller.command.store.BuyCommand;
import com.g51.pokemon.controller.command.store.SellCommand;
import com.g51.pokemon.controller.states.*;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.model.map.*;
import com.g51.pokemon.model.menu.Menu;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.store.Store;
import com.g51.pokemon.view.MusicPlayer;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StateTests {
    private MusicPlayer musicPlayer;
    private TerminalScreen screen;
    private Battle battle;
    private Map<Integer, Attack> map=new HashMap<>();
    private Menu menu;
    private Pokedex pokedex;
    private Route route;
    private Building building;
    private Store store;
    private Player player;

    @BeforeEach
    public void prepare() {
        musicPlayer = mock(MusicPlayer.class);
        doNothing().when(musicPlayer).playSoundEffect("PressEnterSE");
        screen = mock(TerminalScreen.class);
        when(screen.newTextGraphics()).thenReturn(mock(TextGraphics.class));

        battle = mock(Battle.class);

        map.put(0,new Attack("Cut",50,0.95, Types.NORMAL) );
        map.put(1,new Attack("Cut",50,0.95, Types.NORMAL) );
        map.put(2,new Attack("Cut",50,0.95, Types.NORMAL) );
        map.put(3,new Attack("Cut",50,0.95, Types.NORMAL) );
        Pokemon pokemon=new Pokemon("N",10,10,10,10, Types.NORMAL);
        pokemon.setAttackSet(map);
        when(battle.getPlayerCreature()).thenReturn(pokemon);
        when(battle.getCreature()).thenReturn(pokemon);
        when(battle.getTurn()).thenReturn(true);

        menu = mock(Menu.class);
        when(menu.getOption()).thenReturn(1);

        pokedex = mock(Pokedex.class);

        List<Pokemon> creatures = new ArrayList<>();
        creatures.add(new Pokemon("test",10,10,10,10, Types.NORMAL));
        creatures.add(new Pokemon("test",10,10,10,10, Types.NORMAL));
        when(pokedex.getCreatures()).thenReturn(creatures);

        route = Mockito.mock(Route.class);
        List<Element> elements = new ArrayList<>();
        elements.add(new Player(10,10));
        elements.add(new Wall(20,20));
        elements.add(new Grass(30,30));
        elements.add(new BuildingDoor(40, 40));
        when(route.getAllElements()).thenReturn(elements);
        when(route.inBuilding()).thenReturn(-1);

        building = mock(Building.class);
        List<Element> elementsbuilding = new ArrayList<>();
        elementsbuilding.add(new Player(10,10));
        elementsbuilding.add(new Wall(20,20));
        elementsbuilding.add(new BuildingDoor(30,30));
        elementsbuilding.add(new Heal(40,40));
        when(building.getAllElements()).thenReturn(elementsbuilding);
        when(building.isHealingPosition()).thenReturn(false).thenReturn(true);
        when(building.isLeavingBuilding()).thenReturn(false);

        store = mock(Store.class);
        when(store.getPlayerMoney()).thenReturn(900);
        when(store.getMessage()).thenReturn("Test");

        player = mock(Player.class);
        when(player.getTeam()).thenReturn(creatures);
        when(player.getPokeballs()).thenReturn(1);
    }
    @Test
    public void BagStateTest(){
        BagState bagState = new BagState(mock(PauseState.class),player);
        assertEquals(BackCommand.class,bagState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());
        assertEquals(DoNothingCommand.class,bagState.getNextCommand(new KeyStroke(KeyType.ArrowRight)).getClass());
    }

    @Test
    public void BattleStateTest(){
        BattleState battleState = new BattleState(battle,mock(GameState.class));
        battleState.draw(screen);
        assertEquals(SelectAttackCommand.class,battleState.getNextCommand(KeyStroke.fromString("1")).getClass());
        assertEquals(SelectAttackCommand.class,battleState.getNextCommand(KeyStroke.fromString("2")).getClass());
        assertEquals(SelectAttackCommand.class,battleState.getNextCommand(KeyStroke.fromString("3")).getClass());
        assertEquals(SelectAttackCommand.class,battleState.getNextCommand(KeyStroke.fromString("4")).getClass());
        assertEquals(CatchPokemonCommand.class,battleState.getNextCommand(KeyStroke.fromString("c")).getClass());
        assertEquals(RunCommand.class,battleState.getNextCommand(KeyStroke.fromString("R")).getClass());
        assertEquals(SwitchPokemonCommand.class,battleState.getNextCommand(KeyStroke.fromString("S")).getClass());
        assertEquals(DoNothingCommand.class,battleState.getNextCommand(new KeyStroke(KeyType.ArrowLeft)).getClass());

        when(battle.endedBattle()).thenReturn(true);
        assertEquals(EndBattleCommand.class,battleState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());

        when(battle.endedBattle()).thenReturn(false);
        when(battle.getTurn()).thenReturn(false);
        assertEquals(OpponentTurnCommand.class,battleState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
    }

    @Test
    public void BuildingStateTest(){
        BuildingState buildingState = new BuildingState(building,mock(GameState.class));
        buildingState.draw(screen);
        assertEquals(PauseCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());

        assertEquals(MoveDownCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).getClass());
        assertEquals(MoveUpCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.ArrowUp)).getClass());
        assertEquals(MoveLeftCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.ArrowLeft)).getClass());
        assertEquals(MoveRightCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.ArrowRight)).getClass());
        assertEquals(HealCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        when(building.isHealingPosition()).thenReturn(false);
        assertEquals(DoNothingCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        when(building.isLeavingBuilding()).thenReturn(true);
        buildingState.getNextCommand(new KeyStroke(KeyType.ArrowUp));
        verify(building,times(1)).changeLeaveBuilding();
        assertEquals(LeaveBuildingCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).getClass());

        assertEquals(DoNothingCommand.class,buildingState.getNextCommand(new KeyStroke(KeyType.Backspace)).getClass());
    }

    @Test
    public void InstructionsStateTest(){
        StartState start = mock(StartState.class);
        InstructionsState instructionsState = new InstructionsState(start);

        assertEquals(MainMenuCommand.class,instructionsState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());
        assertEquals(DoNothingCommand.class,instructionsState.getNextCommand(new KeyStroke(KeyType.ArrowRight)).getClass());
        assertEquals(start, instructionsState.getPrev());
    }

    @Test
    public void PauseStateTest(){
        PauseState pauseState = new PauseState(mock(GameState.class));
        pauseState.draw(screen);
        assertEquals(PauseCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());
        assertEquals(PreviousOptionCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.ArrowUp)).getClass());
        assertEquals(NextOptionCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).getClass());
        assertEquals(PokedexCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        pauseState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).execute(mock(Game.class));
        assertEquals(TeamCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        pauseState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).execute(mock(Game.class));
        assertEquals(BagCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        pauseState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).execute(mock(Game.class));
        assertEquals(StoreCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        pauseState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).execute(mock(Game.class));
        assertEquals(SaveCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        pauseState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).execute(mock(Game.class));
        assertEquals(QuitCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        assertEquals(DoNothingCommand.class,pauseState.getNextCommand(new KeyStroke(KeyType.ArrowLeft)).getClass());
    }

    @Test
    public void PokedexStateTest(){
        PokedexState pokedexState = new PokedexState(mock(PauseState.class),pokedex);
        pokedexState.draw(screen);
        assertEquals(BackCommand.class,pokedexState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());
        assertEquals(PokedexPreviousCommand.class,pokedexState.getNextCommand(new KeyStroke(KeyType.ArrowUp)).getClass());
        assertEquals(PokedexNextCommand.class,pokedexState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).getClass());
        assertEquals(DoNothingCommand.class,pokedexState.getNextCommand(new KeyStroke(KeyType.ArrowLeft)).getClass());
    }

    @Test
    public void RouteStateTest(){
        RouteState routeState = new RouteState(route);
        routeState.draw(screen);
        assertEquals(PauseCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());
        assertEquals(MoveUpCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.ArrowUp)).getClass());
        assertEquals(MoveDownCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).getClass());
        assertEquals(MoveLeftCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.ArrowLeft)).getClass());
        assertEquals(MoveRightCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.ArrowRight)).getClass());
        assertEquals(DoNothingCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        when(route.isBattleFound()).thenReturn(true);
        assertEquals(BeginBattleCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.ArrowUp)).getClass());
        verify(route, times(1)).changeBattleFound();
        when(route.isBattleFound()).thenReturn(false);
        when(route.inBuilding()).thenReturn(0);
        assertEquals(EnterBuildingCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.ArrowUp)).getClass());
        assertEquals(MoveDownCommand.class,routeState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).getClass());
        verify(route, times(1)).changeInBuilding();

        routeState.leftBuilding();
        verify(route, times(1)).movePlayerToBuildingDoor();
    }

    @Test
    public void StartStateTest(){
        StartState startState = new StartState();
        startState.draw(screen);

        assertEquals(PreviousOptionCommand.class,startState.getNextCommand(new KeyStroke(KeyType.ArrowUp)).getClass());
        assertEquals(NextOptionCommand.class,startState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).getClass());
        assertEquals(StartCommand.class,startState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        startState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).execute(mock(Game.class));
        assertEquals(LoadCommand.class,startState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        startState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).execute(mock(Game.class));
        assertEquals(InstructionsCommand.class,startState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        startState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).execute(mock(Game.class));
        assertEquals(QuitCommand.class,startState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        assertEquals(DoNothingCommand.class,startState.getNextCommand(new KeyStroke(KeyType.ArrowLeft)).getClass());
    }

    @Test
    public void StoreStateTest(){
        StoreState storeState = new StoreState(mock(PauseState.class),player);
        storeState.draw(screen);

        assertEquals(BackCommand.class,storeState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());
        assertEquals(BuyCommand.class,storeState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        assertEquals(SellCommand.class,storeState.getNextCommand(KeyStroke.fromString("S")).getClass());
        assertEquals(DoNothingCommand.class,storeState.getNextCommand(new KeyStroke(KeyType.ArrowLeft)).getClass());
    }

    @Test
    public void SwitchPokemonStateTest(){
        BattleState bState = mock(BattleState.class);
        SwitchPokemonState switchPokemonState = new SwitchPokemonState(bState,player,battle);
        switchPokemonState.draw(screen);

        assertEquals(PreviousOptionCommand.class,switchPokemonState.getNextCommand(new KeyStroke(KeyType.ArrowUp)).getClass());
        assertEquals(NextOptionCommand.class,switchPokemonState.getNextCommand(new KeyStroke(KeyType.ArrowDown)).getClass());
        assertEquals(ChoosePokemonCommand.class,switchPokemonState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());
        assertEquals(ChoosePokemonCommand.class,switchPokemonState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
        assertEquals(DoNothingCommand.class,switchPokemonState.getNextCommand(new KeyStroke(KeyType.ArrowLeft)).getClass());

        assertEquals(bState, switchPokemonState.getPrevState());
    }

    @Test
    public void TeamStateTest(){
        TeamState teamState = new TeamState(mock(PauseState.class),player);
        teamState.draw(screen);

        assertEquals(BackCommand.class,teamState.getNextCommand(new KeyStroke(KeyType.Escape)).getClass());
        assertEquals(DoNothingCommand.class,teamState.getNextCommand(new KeyStroke(KeyType.Enter)).getClass());
    }
}
