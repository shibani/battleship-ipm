package battleshipipm;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameTest {

    @Mock
    private Board mockedBoard;

    @Mock
    private BoardCLI mockedBoardCli;

    @Mock
    private Player mockedPlayer;

    @InjectMocks
    private Game mockedGame;

    @Before
    public void beforeSetup() {
        initMocks(this);
    }

    @Test
    public void config() {
        Game testGame = new Game();
        testGame.config("Player1");
        assertEquals(100, testGame.getCurrentBoard().getTotalPositions());
    }

    @Test
    public void configSetsThePlayer() {
        Game testGame = new Game();
        Game spyGame = Mockito.spy(testGame);

        spyGame.config("Player1");

        verify(spyGame, times(1)).setHuman("Player1");
    }

    @Test
    public void configSetsTheOpponent() {
        Game testGame = new Game();
        Game spyGame = Mockito.spy(testGame);

        spyGame.config("Player1");

        verify(spyGame, times(1)).setComputer();
    }

    @Test
    public void configSetsTheBoardCLI() {
        Game testGame = new Game();
        Game spyGame = Mockito.spy(testGame);

        spyGame.config("Player1");

        verify(spyGame, times(1)).setBoardCli();
    }

    @Test
    public void setBoardCli() {
        final Game testGame = new Game();
        testGame.config("Player1");

        assertThat(testGame.getBoardCli(), instanceOf(BoardCLI.class));
    }

    @Test
    public void getBoardCli() throws NoSuchFieldException, IllegalAccessException {
        final Game testGame = new Game();
        final BoardCLI testBoardCLI = new BoardCLI();

        final Field field = testGame.getClass().getDeclaredField("boardCli");
        field.setAccessible(true);
        field.set(testGame, testBoardCLI);

        final BoardCLI result = testGame.getBoardCli();

        assertEquals(testBoardCLI, result);
    }

    @Test
    public void setHuman() throws NoSuchFieldException, IllegalAccessException {
        Game testGame = new Game();
        testGame.config("Player1");
        final Field field = testGame.getClass().getDeclaredField("human");
        field.setAccessible(true);

        assertThat(field.get(testGame), instanceOf(Human.class));
    }

    @Test
    public void getHuman() throws NoSuchFieldException, IllegalAccessException {
        final Game testGame = new Game();
        final Human testHuman = new Human("Player2");
        final Field field = testGame.getClass().getDeclaredField("human");
        field.setAccessible(true);
        field.set(testGame, testHuman);

        final Human result = testGame.getHuman();

        assertEquals(testHuman, result);
    }

    @Test
    public void setComputer() {
        final Game testGame = new Game();
        testGame.setComputer();

        assertThat(testGame.getComputer(), instanceOf(Computer.class));
    }

    @Test
    public void getComputer() {
        final Game testGame = new Game();
        testGame.setComputer();

        assertEquals("Computer", testGame.getComputer().getName());
    }

    @Test
    public void setMode() throws IllegalAccessException, NoSuchFieldException {
        final Game testGame = new Game();
        final Field field = testGame.getClass().getDeclaredField("mode");
        field.setAccessible(true);
        field.set(testGame, "dev");

        assertNotNull(testGame.getMode());
    }

    @Test
    public void getMode() throws NoSuchFieldException, IllegalAccessException {
        final Game testGame = new Game();

        final Field field = testGame.getClass().getDeclaredField("mode");
        field.setAccessible(true);
        field.set(testGame, "dev");
        String result = testGame.getMode();

        assertEquals("dev", result);
    }

    @Test
    public void getModeAlwaysReturnsAString() {
        final Game testGame = new Game();

        String result = testGame.getMode();

        assertThat(testGame.getMode(), instanceOf(String.class));
    }

    @Test
    public void setCurrentPlayerSetsTheFirstPlayerIfItIsNull() {
        final Game testGame = new Game();
        testGame.config("Player1");

        assertEquals("Player1", testGame.getCurrentPlayer().getName());
    }

    @Test
    public void setCurrentPlayerCanSwitchThePlayer() {
        final Game testGame = new Game();
        testGame.config("Player1");
        testGame.setCurrentPlayer();

        assertEquals("Computer", testGame.getCurrentPlayer().getName());
    }

    @Test
    public void getCurrentPlayer() throws NoSuchFieldException, IllegalAccessException {
        final Game testGame = new Game();
        final Human testHuman = new Human("Computer");

        final Field field = testGame.getClass().getDeclaredField("currentPlayer");
        field.setAccessible(true);
        field.set(testGame, testHuman);

        final Player resultPlayer = testGame.getCurrentPlayer();

        assertEquals("Computer", resultPlayer.getName());
    }

    @Test
    public void getCurrentBoard(){
        final Game testGame = new Game();
        testGame.config("Player1");
        testGame.setCurrentPlayer();
    }

    @Test
    public void printBoardNoArgs() {
        doNothing().when(mockedBoardCli).printBoard(mockedBoard, mockedGame.getMode());
        mockedGame.printBoard(mockedBoard, mockedGame.getMode());

        verify(mockedBoardCli, times(1)).printBoard(mockedBoard, mockedGame.getMode());
    }

    @Test
    public void printBoardOverloaded() {
        doNothing().when(mockedBoardCli).printBoard(mockedBoard, mockedGame.getMode());
        mockedGame.printBoard(mockedBoard, mockedGame.getMode());

        verify(mockedBoardCli, times(1)).printBoard(mockedBoard, mockedGame.getMode());
    }

    @Test
    public void validMove() {
        String[] intArray = new String[100];
        Arrays.fill(intArray, " ");
        ArrayList<String> positions = new ArrayList<>(
                Arrays.asList(intArray));

        when(mockedBoard.getTotalPositions()).thenReturn(100);
        when(mockedBoard.getPositions()).thenReturn(positions);
        when(mockedGame.getCurrentBoard()).thenReturn(mockedBoard);
        when(mockedBoard.coordsToPosition(anyString())).thenReturn(33);
        when(mockedBoard.isEmpty(anyInt())).thenReturn(true);

        mockedGame.validMove(30);

        verify(mockedBoard, times(1)).isEmpty(anyInt());
    }

    @Test
    public void convertPlayerMoveToInt() {
        String[] intArray = new String[100];
        Arrays.fill(intArray, " ");
        ArrayList<String> positions = new ArrayList<>(
                Arrays.asList(intArray));
        when(mockedBoard.getTotalPositions()).thenReturn(100);
        when(mockedBoard.getPositions()).thenReturn(positions);
        when(mockedGame.getCurrentBoard()).thenReturn(mockedBoard);
        when(mockedBoard.coordsToPosition(anyString())).thenReturn(33);
        when(mockedBoard.isEmpty(anyInt())).thenReturn(true);
        mockedGame.convertPlayerMoveToInt("d3");

        verify(mockedBoard, times(1)).coordsToPosition(anyString());
    }

    @Test
    public void makeMove() {
        doNothing().when(mockedBoard).addMarker(30);
        when(mockedGame.getCurrentBoard()).thenReturn(mockedBoard);
        when(mockedBoard.checkForHit(30)).thenReturn("hit");
        mockedGame.makeMove(30);

        verify(mockedBoard, times(1)).addMarker(30);
    }

    @Test
    public void printStatus() {
        doNothing().when(mockedBoardCli).printStatus("Player1", "miss");
        when(mockedGame.getCurrentBoard()).thenReturn(mockedBoard);
        when(mockedGame.getCurrentBoard().checkForHit(30)).thenReturn("hit");
        when(mockedBoard.shipIsSunk(30)).thenReturn("none");
        mockedGame.printStatus("Player1", 30);

        verify(mockedBoardCli, times(1)).printStatus("Player1", "hit");
    }

    @Test
    public void printStatus1() {
        doNothing().when(mockedBoardCli).printStatus(anyString(), anyString());
        when(mockedGame.getCurrentBoard()).thenReturn(mockedBoard);
        when(mockedGame.getCurrentBoard().checkForHit(30)).thenReturn("hit");
        when(mockedBoard.shipIsSunk(30)).thenReturn("P");
        mockedGame.printStatus("Player1", 30);

        verify(mockedBoard, times(1)).shipIsSunk(30);
    }

    @Test
    public void printStatus2() {
        doNothing().when(mockedBoardCli).printStatus(anyString(), anyString());
        when(mockedGame.getCurrentBoard()).thenReturn(mockedBoard);
        when(mockedGame.getCurrentBoard().checkForHit(30)).thenReturn("hit");
        when(mockedBoard.shipIsSunk(30)).thenReturn("P");
        mockedGame.printStatus("Player1", 30);

        verify(mockedBoardCli, times(1)).printSunkStatus(anyString(), anyString());
    }

    @Test
    public void printStatus3() {
        doNothing().when(mockedBoardCli).printStatus(anyString(), anyString());
        when(mockedGame.getCurrentBoard()).thenReturn(mockedBoard);
        when(mockedGame.getCurrentBoard().checkForHit(30)).thenReturn("miss");
        when(mockedBoard.shipIsSunk(30)).thenReturn("P");
        mockedGame.printStatus("Player1", 30);

        verify(mockedBoardCli, times(1)).printStatus("Player1", "miss");
    }

    @Ignore
    public void isOver() {
        when(mockedBoard.isFull()).thenReturn(true);
        boolean result = mockedGame.isOver();

        assertTrue(result);
    }

    @Test
    public void allShipsAreSunk() {
        when(mockedGame.getCurrentBoard()).thenReturn(mockedBoard);
        when(mockedBoard.allShipsAreSunk()).thenReturn(true);
        boolean result = mockedGame.isOver();

        assertTrue(result);
    }

    @Test
    public void getPlayerBoard(){

    }

    @Test
    public void getOpponentBoard(){

    }
}