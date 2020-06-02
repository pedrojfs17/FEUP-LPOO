package com.g51.pokemon.controller.commandtests;

import com.g51.pokemon.controller.Game;
import com.g51.pokemon.controller.states.*;
import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.map.Building;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.map.Route;
import com.g51.pokemon.model.pokedex.Pokedex;
import com.g51.pokemon.controller.command.state.*;
import com.g51.pokemon.view.MusicPlayer;
import com.g51.pokemon.view.gui.Gui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StateCommandTests {
    private Gui gui;
    private Player player;
    private Pokedex pokedex;
    private Route route;
    private MusicPlayer musicPlayer;

    @BeforeEach
    public void prepare(){
        gui=mock(Gui.class);
        player=mock(Player.class);
        pokedex=mock(Pokedex.class);
        route=mock(Route.class);
        musicPlayer=mock(MusicPlayer.class);
        when(player.getCreature(0)).thenReturn(mock(Pokemon.class));
        when(pokedex.getRandomPokemon()).thenReturn(mock(Pokemon.class));
        when(route.getBuilding()).thenReturn(mock(Building.class));

    }
    @Test
    public void PauseCommandTest() {
        RouteState initialState = mock(RouteState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        PauseCommand command = new PauseCommand();

        command.execute(game);

        verify(musicPlayer, times(1)).playSoundEffect("PauseSE");
        assertTrue(game.getState() instanceof PauseState);
        verify(musicPlayer, times(1)).pauseMusic();

        command.execute(game);
        verify(musicPlayer, times(2)).playSoundEffect("PauseSE");
        verify(musicPlayer, times(1)).resumeMusic();

        assertEquals(initialState, game.getState());
    }

    @Test
    public void PokedexCommandTest() {
        PauseState initialState = mock(PauseState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        PokedexCommand command = new PokedexCommand();

        command.execute(game);

        verify(musicPlayer, times(1)).playSoundEffect("PokedexSE");
        assertTrue(game.getState() instanceof PokedexState);
    }

    @Test
    public void BackCommandTest() {
        PokedexState initialState = mock(PokedexState.class);
        when(initialState.getPrev()).thenReturn(mock(PauseState.class));

        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        BackCommand command = new BackCommand();

        command.execute(game);

        verify(musicPlayer, times(1)).playSoundEffect("PauseSE");
        assertTrue(game.getState() instanceof PauseState);
    }

    @Test
    public void BattleCommandTest() {
        RouteState initialState = mock(RouteState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        BeginBattleCommand command = new BeginBattleCommand();

        command.execute(game);

        verify(musicPlayer, times(1)).setBackgroundMusic("Battle");
        assertTrue(game.getState() instanceof BattleState);

        EndBattleCommand command2 = new EndBattleCommand(Battle.STATE.WIN);

        command2.execute(game);

        verify(musicPlayer, times(1)).setBackgroundMusic("Route");
        assertEquals(initialState, game.getState());

        BeginBattleCommand command3 = new BeginBattleCommand();

        command3.execute(game);

        verify(musicPlayer, times(2)).setBackgroundMusic("Battle");
        assertTrue(game.getState() instanceof BattleState);

        EndBattleCommand command4 = new EndBattleCommand(Battle.STATE.LOSE);

        command4.execute(game);

        verify(musicPlayer, times(2)).setBackgroundMusic("Route");
        assertEquals(initialState, game.getState());
        verify(initialState, times(1)).leftBuilding();
    }

    @Test
    public void BuildingCommand(){
        RouteState initialState = mock(RouteState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        EnterBuildingCommand command = new EnterBuildingCommand();

        command.execute(game);

        verify(musicPlayer, times(1)).playSoundEffect("DoorSE");
        assertTrue(game.getState() instanceof BuildingState);

        LeaveBuildingCommand command2 = new LeaveBuildingCommand();

        command2.execute(game);

        verify(musicPlayer, times(2)).playSoundEffect("DoorSE");
        assertEquals(initialState, game.getState());
        verify(initialState, times(1)).leftBuilding();
    }

    @Test
    public void StartCommandTest(){
        StartState initialState = mock(StartState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        StartCommand startCommand = new StartCommand();
        assertTrue(game.getState() instanceof StartState);
        startCommand.execute(game);
        verify(musicPlayer, times(1)).playSoundEffect("PauseSE");
        verify(musicPlayer, times(1)).setBackgroundMusic("Route");
        assertTrue(game.getState() instanceof RouteState);
    }
    @Test
    public void StoreCommandTest(){
        PauseState initialState = mock(PauseState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        StoreCommand storeCommand = new StoreCommand();
        assertTrue(game.getState() instanceof PauseState);
        storeCommand.execute(game);
        verify(musicPlayer, times(1)).playSoundEffect("PauseSE");
        assertTrue(game.getState() instanceof StoreState);
    }

    @Test
    public void TeamCommandTest(){
        PauseState initialState = mock(PauseState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        TeamCommand teamCommand = new TeamCommand();
        assertTrue(game.getState() instanceof PauseState);
        teamCommand.execute(game);
        verify(musicPlayer, times(1)).playSoundEffect("PauseSE");
        assertTrue(game.getState() instanceof TeamState);
    }

    @Test
    public void BagCommandTest(){
        PauseState initialState = mock(PauseState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        BagCommand bagCommand = new BagCommand();
        assertTrue(game.getState() instanceof PauseState);
        bagCommand.execute(game);
        verify(musicPlayer, times(1)).playSoundEffect("PauseSE");
        assertTrue(game.getState() instanceof BagState);
    }

    @Test
    public void InstructionsCommandTest(){
        StartState initialState = mock(StartState.class);
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        InstructionsCommand instructionsCommand = new InstructionsCommand();
        assertTrue(game.getState() instanceof StartState);
        instructionsCommand.execute(game);
        verify(musicPlayer, times(1)).playSoundEffect("PauseSE");
        assertTrue(game.getState() instanceof InstructionsState);
    }

    @Test
    public void MainMenuCommandTest(){
        InstructionsState initialState = mock(InstructionsState.class);
        when(initialState.getPrev()).thenReturn(mock(StartState.class));
        Game game = new Game(initialState, gui, player, pokedex, route, musicPlayer);

        MainMenuCommand mainMenuCommand = new MainMenuCommand();
        assertTrue(game.getState() instanceof InstructionsState);
        mainMenuCommand.execute(game);
        verify(musicPlayer, times(1)).playSoundEffect("PauseSE");
        assertTrue(game.getState() instanceof StartState);
    }
}
