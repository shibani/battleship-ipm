package battleshipipm;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class AppTest {

    @Mock
    private Game mockedGame;

    @Mock
    private CLI mockedCli;

    @Mock
    private Player mockedPlayer;

    @InjectMocks
    private App mockedApp;


    @Before
    public void beforeSetup() {
        initMocks(this);
    }

    @Test
    public void playGameCallsSetup() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        doNothing().when(spyApp).setup();
        when(spyApp.gameOver()).thenReturn(false).thenReturn(true);
        when(spyApp.getCurrentPlayer()).thenReturn(mockedPlayer);
        doNothing().when(spyApp).gameLoop(mockedPlayer);

        spyApp.playGame();
        verify(spyApp, times(1)).setup();
    }

    @Test
    public void playGameCallsTheGameLoop() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        doNothing().when(spyApp).setup();
        when(spyApp.gameOver()).thenReturn(false).thenReturn(true);
        when(spyApp.getCurrentPlayer()).thenReturn(mockedPlayer);
        doNothing().when(spyApp).gameLoop(mockedPlayer);

        spyApp.playGame();

        verify(spyApp, times(1)).gameLoop(mockedPlayer);
    }

    @Test
    public void playGameCanSwitchTheCurrentPlayer() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        doNothing().when(spyApp).setup();
        doReturn(false).doReturn(true).when(spyApp).gameOver();
        when(spyApp.getCurrentPlayer()).thenReturn(mockedPlayer);
        doNothing().when(spyApp).gameLoop(mockedPlayer);

        spyApp.playGame();

        verify(spyApp, atLeast(1)).getCurrentPlayer();
    }

    @Test
    public void playGameChecksIfTheGameIsOver() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        doNothing().when(spyApp).setup();
        when(spyApp.gameOver()).thenReturn(true);
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, atLeast(2)).gameOver();
    }

    @Test
    public void playGameCanCallEndGameWhenTheGameIsOver() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        doNothing().when(spyApp).setup();
        doReturn(true).when(spyApp).gameOver();
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, times(1)).endGame((Player)isNull());
    }

    @Test
    public void setup() {
        mockedApp.setup();

        verify(mockedCli, times(1)).welcome();
    }

    @Test
    public void setup1() {
        mockedApp.setup();

        verify(mockedCli, times(1)).getPlayerName();
    }

    @Test
    public void setup2() {
        when(mockedCli.getPlayerName()).thenReturn(("Player1"));
        mockedApp.setup();

        verify(mockedGame, times(1)).config("Player1");
    }

    @Test
    public void setupSetstheGameToReceiveMessagesBackFromTheCLI() {
        when(mockedCli.getPlayerName()).thenReturn(("Player1"));
        mockedApp.setup();

        verify(mockedCli, times(1)).setGame(any(Game.class));
    }

    @Test
    public void gameLoop() {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedCli, times(1)).getPlayerMove(mockedPlayer.getName());
    }

    @Test
    public void gameLoop1() {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).convertPlayerMoveToInt((String)isNull());
    }

    @Test
    public void gameLoop2() {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).validMove(anyInt());
    }

    @Test
    public void gameLoop3() {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).makeMove(anyInt());
    }

    @Test
    public void gameLoop4() {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).printBoard();
    }

    @Test
    public void gameLoop5() {
        Player testPlayer = new Player("Player1");
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());
        mockedApp.gameLoop(testPlayer);
        verify(mockedGame, times(1)).printStatus(testPlayer.getName(), 30);
    }

    @Test
    public void gameLoop6() {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(false).thenReturn(false).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedCli).tryAgain();

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedCli, times(2)).tryAgain();
    }

    @Test
    public void gameOver() {
        when(mockedApp.gameOver()).thenReturn(false);
        mockedApp.gameOver();
        verify(mockedGame, times(1)).isOver();
    }

    @Test
    public void endGame() {
        when(mockedApp.gameOver()).thenReturn(true);

        mockedApp.playGame();
        verify(mockedCli, times(1)).endGame((Player)isNull());
    }

    @Test
    public void getPlayer() {
        when(mockedApp.getPlayer()).thenReturn(mockedPlayer);
        when(mockedApp.gameOver()).thenReturn(true);

        mockedApp.playGame();
        verify(mockedGame, times(1)).getPlayer();
    }

    @Test
    public void getCurrentPlayerCanSwitchThePlayer() {
        when(mockedApp.getCurrentPlayer()).thenReturn(mockedPlayer);
        when(mockedApp.gameOver()).thenReturn(false);

        mockedApp.getCurrentPlayer();
        verify(mockedGame, times(1)).getCurrentPlayer();
    }

    @Test
    public void getCurrentPlayerReturnsThePlayer() {
        when(mockedApp.getCurrentPlayer()).thenReturn(mockedPlayer);
        when(mockedApp.gameOver()).thenReturn(false);

        mockedApp.getCurrentPlayer();
        verify(mockedGame, times(1)).getCurrentPlayer();
    }
}
