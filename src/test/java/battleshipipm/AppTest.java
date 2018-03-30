package battleshipipm;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class AppTest {

    @Mock
    private Game mockedGame;

    @Mock
    private CLI mockedCli;

    @Mock
    private Player mockedPlayer;

    @Mock
    private Human mockedHuman;

    @Mock
    private Human mockedComputer;

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

        verify(spyApp, times(1)).endGame((Human)isNull());
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
    public void gameLoop1GetsAMove() {
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);
        when(mockedGame.validMove(anyInt())).thenReturn(false).thenReturn(true);
        spyApp.gameLoop(mockedPlayer);

        verify(spyApp, times(2)).getMove(any(Player.class));
    }

    @Test
    public void gameLoop2ChecksForValidMove() {
        when(mockedApp.getMove(mockedPlayer)).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).validMove(anyInt());
    }

    @Test
    public void gameLoop3MakesTheMove() {
        when(mockedApp.getMove(mockedPlayer)).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, times(1)).makeMove(anyInt());
    }

    @Test
    public void gameLoop4PrintsTheBoard() {
        when(mockedApp.getMove(mockedPlayer)).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());

        mockedApp.gameLoop(mockedPlayer);
        verify(mockedGame, atLeast(2)).printBoard();
    }

    @Test
    public void gameLoop5CanCheckIfMoveResultedInHitOrSunkShip() {
        Human testHuman = new Human("Player1");
        when(mockedApp.getMove(mockedPlayer)).thenReturn(30);
        when(mockedGame.validMove(anyInt())).thenReturn(true);
        doNothing().when(mockedGame).makeMove(anyInt());
        doNothing().when(mockedGame).printBoard();
        doNothing().when(mockedGame).printStatus(anyString(),anyInt());
        mockedApp.gameLoop(testHuman);
        verify(mockedGame, times(1)).printStatus(testHuman.getName(), 30);
    }

    @Test
    public void gameLoop6CanAskthePlayerToTryAgain() {
        when(mockedApp.getMove(mockedPlayer)).thenReturn(30);
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
        verify(mockedCli, times(1)).endGame((Human)isNull());
    }

    @Test
    public void getPlayer() {
        when(mockedApp.getHuman()).thenReturn(mockedHuman);
        when(mockedApp.gameOver()).thenReturn(true);

        mockedApp.playGame();
        verify(mockedGame, times(1)).getHuman();
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

    @Test
    public void getMoveChecksIfPlayerIsComputerOrHuman(){
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        Player testPlayer = new Human("Player1");

        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt("d3")).thenReturn(30);

        spyApp.getMove(testPlayer);
        verify(spyApp, times(1)).getHuman();
    }

    @Test
    public void getMoveCallsGetPlayerMove(){
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        mockedGame.setHuman("Player1");

        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt("d3")).thenReturn(30);
        when(spyApp.getHuman()).thenReturn(mockedHuman);

        Human testHuman = mockedGame.getHuman();

        spyApp.getMove(testHuman);
        verify(mockedCli, times(1)).getPlayerMove(testHuman.getName());
    }

    @Test
    public void getMoveCallsConvertPlayerMove(){
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        mockedGame.setHuman("Player1");

        when(mockedCli.getPlayerMove(anyString())).thenReturn("d3");
        when(mockedGame.convertPlayerMoveToInt("d3")).thenReturn(30);
        when(spyApp.getHuman()).thenReturn(mockedHuman);

        Human testHuman = mockedGame.getHuman();

        spyApp.getMove(testHuman);
        verify(mockedGame, times(1)).convertPlayerMoveToInt((String)isNull());
    }

    @Test
    public void getMoveCallsGetComputerMove(){
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        Computer testComputer = new Computer("Computer");

        spyApp.getMove(testComputer);
        verify(mockedCli, times(1)).getComputerMove(testComputer.getName());
    }

    @Test
    public void getMoveGetsMoveFromTheComputer(){
        App testApp = new App(mockedCli, mockedGame);
        App spyApp = Mockito.spy(testApp);

        Computer testComputer = new Computer("Computer");

        spyApp.getMove(testComputer);
        verify(mockedGame, times(1)).getComputerMove();
    }

    @Test
    public void getHuman() {
        mockedApp.getHuman();
        verify(mockedGame, times(1)).getHuman();
    }

    @Test
    public void getComputer() {
        mockedApp.getComputer();
        verify(mockedGame, times(1)).getComputer();
    }
}
