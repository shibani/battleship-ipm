package battleshipipm;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        testGame.setBoardCli();

        assertThat(testGame.getBoardCli(), instanceOf(BoardCLI.class));
    }

    @Test
    public void getBoardCLI() throws NoSuchFieldException, IllegalAccessException {
        final Game testGame = new Game();
        final BoardCLI testBoardCLI = new BoardCLI();

        final Field field = testGame.getClass().getDeclaredField("boardCli");
        field.setAccessible(true);
        field.set(testGame, testBoardCLI);

        final BoardCLI result = testGame.getBoardCli();

        assertEquals(testBoardCLI, result);
    }

    @Test
    public void config() {
        Game testGame = new Game();
        testGame.config();
        assertEquals(100, testGame.getBoard().getTotalPositions());
    }
}