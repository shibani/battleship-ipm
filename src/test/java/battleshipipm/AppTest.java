package battleshipipm;

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
    public void playGame() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        when(spyApp.setup()).thenReturn(mockedPlayer);
        when(spyApp.gameOver()).thenReturn(false).thenReturn(true);
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, times(2)).setup();
    }

    @Test
    public void playGame1() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        when(spyApp.setup()).thenReturn(mockedPlayer);
        when(spyApp.gameOver()).thenReturn(false).thenReturn(true);
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, times(1)).gameLoop(mockedPlayer);
    }

    @Test
    public void playGame2() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        when(spyApp.setup()).thenReturn(mockedPlayer);
        when(spyApp.gameOver()).thenReturn(true);
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, atLeast(2)).gameOver();
    }

    @Test
    public void playGame3() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        when(spyApp.setup()).thenReturn(mockedPlayer);
        when(spyApp.gameOver()).thenReturn(true);
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, times(1)).endGame(mockedPlayer);
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
}
