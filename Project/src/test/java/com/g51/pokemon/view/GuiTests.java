package com.g51.pokemon.view;

import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.model.map.*;
import com.g51.pokemon.model.menu.Menu;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.store.Store;
import com.g51.pokemon.view.gui.*;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
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

public class GuiTests {
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

        store = mock(Store.class);
        when(store.getPlayerMoney()).thenReturn(900);
        when(store.getMessage()).thenReturn("Test");

        player = mock(Player.class);
        when(player.getTeam()).thenReturn(creatures);
        when(player.getPokeballs()).thenReturn(1);
    }

    @Test
    public void BattleGuiTest(){
        BattleGui battleGui = new BattleGui();
        battleGui.draw(screen,battle);

        verify(screen,times(8)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 40), ' ');
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(4, 25), new TerminalSize(44, 5), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(10,20,battle.getPlayerCreature().toString());
        verify(screen.newTextGraphics(),times(1)).putString(7, 27, "1 "+battle.getPlayerCreature().getAttack(0).toString());


        assertEquals(Gui.KEYS.FIRST,battleGui.getKeyPressed(KeyStroke.fromString("1")));
        assertEquals(Gui.KEYS.SECOND,battleGui.getKeyPressed(KeyStroke.fromString("2")));
        assertEquals(Gui.KEYS.THIRD,battleGui.getKeyPressed(KeyStroke.fromString("3")));
        assertEquals(Gui.KEYS.FOURTH,battleGui.getKeyPressed(KeyStroke.fromString("4")));
        assertEquals(Gui.KEYS.C,battleGui.getKeyPressed(KeyStroke.fromString("c")));
        assertEquals(Gui.KEYS.R,battleGui.getKeyPressed(KeyStroke.fromString("R")));
        assertEquals(Gui.KEYS.S,battleGui.getKeyPressed(KeyStroke.fromString("S")));
        assertEquals(Gui.KEYS.NOTHING,battleGui.getKeyPressed(new KeyStroke(KeyType.ArrowLeft)));

        when(battle.endedBattle()).thenReturn(true);
        assertEquals(Gui.KEYS.NOTHING,battleGui.getKeyPressed(new KeyStroke(KeyType.Enter)));

        when(battle.endedBattle()).thenReturn(false);
        when(battle.getTurn()).thenReturn(false);
        assertEquals(Gui.KEYS.NOTHING,battleGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
    }
    @Test
    public void PauseGuiTest(){
        PauseGui pauseGui = new PauseGui();
        pauseGui.draw(screen, menu);

        verify(screen,times(3)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(20, 15), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(7,2,"Pokedex");
        verify(screen.newTextGraphics(),times(1)).setCharacter(4, 4, Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK);


        assertEquals(Gui.KEYS.ESC,pauseGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.DOWN,pauseGui.getKeyPressed(new KeyStroke(KeyType.ArrowDown)));
        assertEquals(Gui.KEYS.UP,pauseGui.getKeyPressed(new KeyStroke(KeyType.ArrowUp)));
        when(menu.getOption()).thenReturn(0);
        assertEquals(Gui.KEYS.ENTER,pauseGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(menu.getOption()).thenReturn(1);
        assertEquals(Gui.KEYS.ENTER,pauseGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(menu.getOption()).thenReturn(2);
        assertEquals(Gui.KEYS.ENTER,pauseGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(menu.getOption()).thenReturn(3);
        assertEquals(Gui.KEYS.ENTER,pauseGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(menu.getOption()).thenReturn(4);
        assertEquals(Gui.KEYS.ENTER,pauseGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(menu.getOption()).thenReturn(5);
        assertEquals(Gui.KEYS.ENTER,pauseGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        assertEquals(Gui.KEYS.NOTHING,pauseGui.getKeyPressed(new KeyStroke(KeyType.ArrowLeft)));
    }

    @Test
    public void PokedexGuiTest(){
        PokedexGui pokedexGui = new PokedexGui();
        pokedexGui.draw(screen, pokedex);

        verify(screen,times(4)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).putString(10, 11, pokedex.getCreatures().get(1).getName());
        verify(screen.newTextGraphics(),times(1)).setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(6, 6), new TerminalSize(88, 3), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(87,3,"Speed");

        assertEquals(Gui.KEYS.ESC,pokedexGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.DOWN,pokedexGui.getKeyPressed(new KeyStroke(KeyType.ArrowDown)));
        assertEquals(Gui.KEYS.UP,pokedexGui.getKeyPressed(new KeyStroke(KeyType.ArrowUp)));
        assertEquals(Gui.KEYS.NOTHING,pokedexGui.getKeyPressed(new KeyStroke(KeyType.ArrowLeft)));
    }

    @Test
    public void RouteGuiTest() {
        RouteGui routeGui = new RouteGui();
        routeGui.draw(screen,route);

        verify(screen,times(5)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(route.getWidth(), route.getHeight()), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(10, 10, "X");
        verify(screen.newTextGraphics(),times(1)).putString(20, 20, " ");
        verify(screen.newTextGraphics(),times(1)).putString(30, 30, " ");
        verify(screen.newTextGraphics(),times(1)).putString(40, 40, " ");

        assertEquals(Gui.KEYS.ESC,routeGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.DOWN,routeGui.getKeyPressed(new KeyStroke(KeyType.ArrowDown)));
        assertEquals(Gui.KEYS.UP,routeGui.getKeyPressed(new KeyStroke(KeyType.ArrowUp)));
        assertEquals(Gui.KEYS.LEFT,routeGui.getKeyPressed(new KeyStroke(KeyType.ArrowLeft)));
        assertEquals(Gui.KEYS.RIGHT,routeGui.getKeyPressed(new KeyStroke(KeyType.ArrowRight)));
        assertEquals(Gui.KEYS.NOTHING,routeGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(route.isBattleFound()).thenReturn(true);
        assertEquals(Gui.KEYS.NOTHING,routeGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(route.isBattleFound()).thenReturn(false);
        when(route.inBuilding()).thenReturn(0);
        assertEquals(Gui.KEYS.UP,routeGui.getKeyPressed(new KeyStroke(KeyType.ArrowUp)));
        routeGui.getKeyPressed(new KeyStroke(KeyType.ArrowDown));
        //verify(route,times(1)).changeInBuilding();
    }

    @Test
    public void BuildingGuiTest(){
        BuildingGui buildingGui = new BuildingGui();
        buildingGui.draw(screen,building);

        verify(screen,times(5)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(10, 5), new TerminalSize(80, 30), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(10, 10, "X");
        verify(screen.newTextGraphics(),times(2)).setBackgroundColor(TextColor.Factory.fromString("#ffcc66"));
        verify(screen.newTextGraphics(),times(1)).putString(20, 20, " ");
        verify(screen.newTextGraphics(),times(1)).setBackgroundColor(TextColor.Factory.fromString("#996600"));
        verify(screen.newTextGraphics(),times(1)).putString(30, 30, " ");
        verify(screen.newTextGraphics(),times(1)).setBackgroundColor(TextColor.Factory.fromString("#663300"));
        verify(screen.newTextGraphics(),times(1)).putString(40, 40, " ");
        verify(screen.newTextGraphics(),times(1)).setBackgroundColor(TextColor.Factory.fromString("#FF0000"));

        buildingGui.draw(screen,building);
        verify(screen.newTextGraphics(),times(1)).setBackgroundColor(TextColor.Factory.fromString("#00FF00"));

        assertEquals(Gui.KEYS.ESC,buildingGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.DOWN,buildingGui.getKeyPressed(new KeyStroke(KeyType.ArrowDown)));
        assertEquals(Gui.KEYS.UP,buildingGui.getKeyPressed(new KeyStroke(KeyType.ArrowUp)));
        assertEquals(Gui.KEYS.LEFT,buildingGui.getKeyPressed(new KeyStroke(KeyType.ArrowLeft)));
        assertEquals(Gui.KEYS.RIGHT,buildingGui.getKeyPressed(new KeyStroke(KeyType.ArrowRight)));
        assertEquals(Gui.KEYS.ENTER,buildingGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(building.isHealingPosition()).thenReturn(false);
        assertEquals(Gui.KEYS.ENTER,buildingGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(building.isLeavingBuilding()).thenReturn(true);
        //buildingGui.getNextCommand(new KeyStroke(KeyType.ArrowUp));
        assertEquals(Gui.KEYS.DOWN,buildingGui.getKeyPressed(new KeyStroke(KeyType.ArrowDown)));
        //verify(building,times(1)).changeLeaveBuilding();
    }

    @Test
    public void StartGuiTest(){
        StartGui startGui = new StartGui();
        startGui.draw(screen,menu);

        verify(screen,times(3)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 20), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(45,25,"START GAME");
        verify(screen.newTextGraphics(),times(1)).setCharacter(43, (27), Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK);


        assertEquals(Gui.KEYS.DOWN,startGui.getKeyPressed(new KeyStroke(KeyType.ArrowDown)));
        assertEquals(Gui.KEYS.UP,startGui.getKeyPressed(new KeyStroke(KeyType.ArrowUp)));
        when(menu.getOption()).thenReturn(0);
        assertEquals(Gui.KEYS.ENTER,startGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(menu.getOption()).thenReturn(1);
        assertEquals(Gui.KEYS.ENTER,startGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(menu.getOption()).thenReturn(2);
        assertEquals(Gui.KEYS.ENTER,startGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        when(menu.getOption()).thenReturn(3);
        assertEquals(Gui.KEYS.ENTER,startGui.getKeyPressed(new KeyStroke(KeyType.Enter)));

        assertEquals(Gui.KEYS.NOTHING,startGui.getKeyPressed(new KeyStroke(KeyType.ArrowRight)));
    }

    @Test
    public void StoreGuiTest(){
        StoreGui storeGui = new StoreGui();
        storeGui.draw(screen,store);

        verify(screen,times(6)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 20), ' ');
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(6, 12 ), new TerminalSize(88, 3), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(10,10,"Name");
        verify(screen.newTextGraphics(),times(1)).setCharacter(6, 13, Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK);
        verify(screen.newTextGraphics(),times(1)).putString(14,4,"Store");

        assertEquals(Gui.KEYS.ESC,storeGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.ENTER,storeGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        KeyStroke input = mock(KeyStroke.class);
        when(input.getKeyType()).thenReturn(KeyType.Character);
        when(input.getCharacter()).thenReturn('s');
        assertEquals(Gui.KEYS.S,storeGui.getKeyPressed(input));

        assertEquals(Gui.KEYS.NOTHING,storeGui.getKeyPressed(new KeyStroke(KeyType.ArrowRight)));
    }

    @Test
    public void TeamGuiTest(){
        TeamGui teamGui = new TeamGui();
        teamGui.draw(screen,player);

        verify(screen,times(4)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).putString(10, 17, player.getTeam().get(1).getName());
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 20), ' ');
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(6, 12), new TerminalSize(88, 3), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(10,10,"Name");

        assertEquals(Gui.KEYS.ESC,teamGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.NOTHING,teamGui.getKeyPressed(new KeyStroke(KeyType.ArrowRight)));
    }

    @Test
    public void InstructionsGuiTest(){
        InstructionsGui instructionsGui = new InstructionsGui();
        instructionsGui.draw(screen);

        verify(screen,times(3)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 20), ' ');
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(4, 2), new TerminalSize(92, 36), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(8,35,"PRESS ESC TO RETURN TO THE MAIN MENU...");

        assertEquals(Gui.KEYS.ESC,instructionsGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.NOTHING,instructionsGui.getKeyPressed(new KeyStroke(KeyType.ArrowRight)));
    }

    @Test
    public void BagGuiTest(){
        BagGui bagGui = new BagGui();
        bagGui.draw(screen,player);

        verify(screen,times(4)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).putString(77,13,"1");
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 20), ' ');
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(6, 12), new TerminalSize(88, 3), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(10,5,"BAG");

        assertEquals(Gui.KEYS.ESC,bagGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.NOTHING,bagGui.getKeyPressed(new KeyStroke(KeyType.ArrowRight)));
    }

    @Test
    public void SwitchPokemonGuiTest(){
        SwitchPokemonGui switchPokemonGui = new SwitchPokemonGui();
        switchPokemonGui.draw(screen,player,menu);

        verify(screen,times(5)).newTextGraphics();
        verify(screen.newTextGraphics(),times(1)).putString(10, 17, player.getTeam().get(1).getName());
        verify(screen.newTextGraphics(),times(1)).setCharacter(6, 17, Symbols.TRIANGLE_LEFT_POINTING_MEDIUM_BLACK);
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 20), ' ');
        verify(screen.newTextGraphics(),times(1)).fillRectangle(new TerminalPosition(4, 12 ), new TerminalSize(90, 3), ' ');
        verify(screen.newTextGraphics(),times(1)).putString(10,5,"TEAM");

        assertEquals(Gui.KEYS.ESC,switchPokemonGui.getKeyPressed(new KeyStroke(KeyType.Escape)));
        assertEquals(Gui.KEYS.ENTER,switchPokemonGui.getKeyPressed(new KeyStroke(KeyType.Enter)));
        assertEquals(Gui.KEYS.UP,switchPokemonGui.getKeyPressed(new KeyStroke(KeyType.ArrowUp)));
        assertEquals(Gui.KEYS.DOWN,switchPokemonGui.getKeyPressed(new KeyStroke(KeyType.ArrowDown)));
        assertEquals(Gui.KEYS.NOTHING,switchPokemonGui.getKeyPressed(new KeyStroke(KeyType.ArrowRight)));
    }
}
