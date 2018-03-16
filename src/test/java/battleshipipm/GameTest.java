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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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
    public void validMove1() {
        String[] intArray = new String[100];
        Arrays.fill(intArray, " ");
        ArrayList<String> positions = new ArrayList<>(
                Arrays.asList(intArray));
        when(mockedBoard.getTotalPositions()).thenReturn(100);
        when(mockedBoard.getPositions()).thenReturn(positions);
        when(mockedBoard.coordsToPosition(anyString())).thenReturn(33);
        when(mockedBoard.isEmpty(anyInt())).thenReturn(true);
        mockedGame.validMove(30);

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
    public void makeMove1() {
        doNothing().when(mockedBoard).addMarker(30);
        when(mockedBoard.checkForHit(30)).thenReturn("hit");
        mockedGame.makeMove(anyInt());

        verify(mockedBoard, times(1)).checkForHit(anyInt());
    }
}