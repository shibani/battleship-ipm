package battleshipipm;

import org.junit.Test;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameTest {

    @Test
    public void getBoard(){
        Board testBoard = new Board();
        BoardCLI testBcli = new BoardCLI();
        Game testGame = new Game(testBoard, testBcli);

        assertThat(testGame.getBoard(), instanceOf(Board.class));
    }

    @Test
    public void getBoardCli() {
        Board testBoard = new Board();
        BoardCLI testBcli = new BoardCLI();
        Game testGame = new Game(testBoard, testBcli);

        assertThat(testGame.getBoardCli(), instanceOf(BoardCLI.class));
    }
}