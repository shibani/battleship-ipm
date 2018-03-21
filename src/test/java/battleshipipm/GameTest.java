package battleshipipm;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameTest {

    @Test
    public void setBoard() {
        final Game testGame = new Game();
        testGame.setBoard(10);

        assertThat(testGame.getBoard(), instanceOf(Board.class));
    }

    @Test
    public void getBoard() throws NoSuchFieldException, IllegalAccessException {
        final Game testGame = new Game();
        final Board testBoard = new Board();
        testGame.setBoard(10);

        final Field field = testGame.getClass().getDeclaredField("board");
        field.setAccessible(true);
        field.set(testGame, testBoard);

        final Board result = testGame.getBoard();

        assertEquals(testBoard, result);
    }

    @Test
    public void setBoardCli() {
        final Game testGame = new Game();
        Board testBoard = new Board();
        testGame.setBoardCli(testBoard);

        assertThat(testGame.getBoardCli(), instanceOf(BoardCLI.class));
    }

    @Test
    public void getBoardCli() throws NoSuchFieldException, IllegalAccessException {
        final Game testGame = new Game();
        Board testBoard = new Board();
        final BoardCLI testBoardCLI = new BoardCLI(testBoard);

        final Field field = testGame.getClass().getDeclaredField("boardCli");
        field.setAccessible(true);
        field.set(testGame, testBoardCLI);

        final BoardCLI result = testGame.getBoardCli();

        assertEquals(testBoardCLI, result);
    }

    @Test
    public void config() {
        Game testGame = new Game();
        testGame.config("Player1");
        assertEquals(100, testGame.getBoard().getTotalPositions());
    }

    @Test
    public void setPlayer() throws NoSuchFieldException, IllegalAccessException {
        Game testGame = new Game();
        testGame.config("Player1");
        final Field field = testGame.getClass().getDeclaredField("player");
        field.setAccessible(true);

        assertThat(field.get(testGame), instanceOf(Player.class));
    }

    @Test
    public void getPlayer() throws NoSuchFieldException, IllegalAccessException {
        final Game testGame = new Game();
        final Player testPlayer = new Player("Player2");
        final Field field = testGame.getClass().getDeclaredField("player");
        field.setAccessible(true);
        field.set(testGame, testPlayer);

        final Player result = testGame.getPlayer();

        assertEquals(testPlayer, result);
    }

    @Mock
    private Board mockedBoard;
    @Mock
    private BoardCLI mockedBoardCli;

    @InjectMocks
    private Game mockedGame;

    @Before
    public void beforeSetup() {
        initMocks(this);
    }

    @Test
    public void validMove() {
        String[] intArray = new String[100];
        Arrays.fill(intArray, " ");
        ArrayList<String> positions = new ArrayList<>(
                Arrays.asList(intArray));
        when(mockedBoard.getTotalPositions()).thenReturn(100);
        when(mockedBoard.getPositions()).thenReturn(positions);
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
        when(mockedBoard.coordsToPosition(anyString())).thenReturn(33);
        when(mockedBoard.isEmpty(anyInt())).thenReturn(true);
        mockedGame.convertPlayerMoveToInt("d3");

        verify(mockedBoard, times(1)).coordsToPosition(anyString());
    }

    @Test
    public void makeMove() {
        doNothing().when(mockedBoard).addMarker(30);
        when(mockedBoard.checkForHit(30)).thenReturn("hit");
        mockedGame.makeMove(anyInt());

        verify(mockedBoard, times(1)).addMarker(anyInt());
    }

    @Test
    public void printBoard() {
        doNothing().when(mockedBoardCli).print(mockedBoard);
        mockedBoardCli.print(mockedBoard);

        verify(mockedBoardCli, times(1)).print(mockedBoard);
    }

    @Test
    public void printStatus() {
        doNothing().when(mockedBoardCli).printStatus("Player1", "miss");
        when(mockedGame.getBoard().checkForHit(30)).thenReturn("hit");
        when(mockedBoard.shipIsSunk(30)).thenReturn("none");
        mockedGame.printStatus("Player1", 30);

        verify(mockedBoardCli, times(1)).printStatus("Player1", "hit");
    }

    @Test
    public void printStatus1() {
        doNothing().when(mockedBoardCli).printStatus(anyString(), anyString());
        when(mockedGame.getBoard().checkForHit(30)).thenReturn("hit");
        when(mockedBoard.shipIsSunk(30)).thenReturn("P");
        mockedGame.printStatus("Player1", 30);

        verify(mockedBoard, times(1)).shipIsSunk(30);
    }

    @Test
    public void printStatus2() {
        doNothing().when(mockedBoardCli).printStatus(anyString(), anyString());
        when(mockedGame.getBoard().checkForHit(30)).thenReturn("hit");
        when(mockedBoard.shipIsSunk(30)).thenReturn("P");
        mockedGame.printStatus("Player1", 30);

        verify(mockedBoardCli, times(1)).printSunkStatus(anyString(), anyString());
    }

    @Test
    public void printStatus3() {
        doNothing().when(mockedBoardCli).printStatus(anyString(), anyString());
        when(mockedGame.getBoard().checkForHit(30)).thenReturn("miss");
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
        when(mockedBoard.allShipsAreSunk()).thenReturn(true);
        boolean result = mockedGame.isOver();

        assertTrue(result);
    }
}