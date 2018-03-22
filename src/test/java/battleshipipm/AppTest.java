package battleshipipm;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.io.IOException;

import static org.junit.Assert.*;
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

    @Ignore
    public void start() throws IOException {
        //Powermockito - test class method here
        App.start();
        verify(mockedApp, times(1)).playGame();
    }

    @Test
    public void playGame() throws IOException {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        when(spyApp.setup()).thenReturn(mockedPlayer);
        when(spyApp.gameOver()).thenReturn(false).thenReturn(true);
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, times(2)).setup();
    }

    @Test
    public void playGame1() throws IOException {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        when(spyApp.setup()).thenReturn(mockedPlayer);
        when(spyApp.gameOver()).thenReturn(false).thenReturn(true);
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, times(1)).gameLoop(mockedPlayer);
    }

    @Test
    public void playGame2() throws IOException {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        when(spyApp.setup()).thenReturn(mockedPlayer);
        when(spyApp.gameOver()).thenReturn(true);
        doNothing().when(spyApp).gameLoop(mockedPlayer);
        spyApp.playGame();

        verify(spyApp, times(2)).gameOver();
    }

    @Test
    public void setup() throws IOException {
        mockedApp.setup();

        verify(mockedCli, times(1)).setup();
    }

    @Test
    public void setup2() throws IOException {
        when(mockedCli.setup()).thenReturn(("Player1"));
        mockedApp.setup();

        verify(mockedGame, times(1)).config(anyString());
    }

    @Test
    public void gameLoop() throws IOException {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        when(mockedGame.makeMove(anyInt())).thenReturn("miss");
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyString());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedCli, times(1)).getPlayerMove(mockedPlayer.getName());
    }

    @Test
    public void gameLoop1() throws IOException {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        when(mockedGame.makeMove(anyInt())).thenReturn("miss");
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyString());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).convertPlayerMoveToInt(isNull());
    }

    @Test
    public void gameLoop2() throws IOException {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        when(mockedGame.makeMove(anyInt())).thenReturn("miss");
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyString());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).validMove(anyInt());
    }

    @Test
    public void gameLoop3() throws IOException {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        when(mockedGame.makeMove(anyInt())).thenReturn("miss");
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyString());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).makeMove(anyInt());
    }

    @Test
    public void gameLoop4() throws IOException {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        when(mockedGame.makeMove(anyInt())).thenReturn("miss");
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyString());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).printBoard();
    }

    @Test
    public void gameLoop5() throws IOException {
        Player testPlayer = new Player("Player1");
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        when(mockedGame.makeMove(anyInt())).thenReturn("hit");
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyString());

        mockedApp.gameLoop(testPlayer);
        verify(mockedGame, times(1)).printStatus(testPlayer.getName(), "hit");
    }

    @Test
    public void gameLoop6() throws IOException {
        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt(anyString())).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(false).thenReturn(false).thenReturn(true);
        when(mockedGame.makeMove(anyInt())).thenReturn("miss");
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedCli).tryAgain();

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedCli, times(2)).tryAgain();
    }

    @Test
    public void gameOver() {
        mockedApp.gameOver();
        verify(mockedGame, times(1)).isOver();
    }
}


