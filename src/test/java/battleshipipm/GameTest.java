package battleshipipm;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameTest {

    @Mock
    BoardCLI mockedBoardCli;
    @Mock
    Board mockedBoard;


    @InjectMocks
    private Game mockedGame;

    @Before
    public void beforeSetup() {
        initMocks(this);
    }

    @Test
    public void getBoard(){
        mockedGame.config();
        assertThat(mockedGame.getBoard(), instanceOf(Board.class));
    }

    @Test
    public void getBoardCli() {
        mockedGame.config();
        assertThat(mockedGame.getBoardCli(), instanceOf(BoardCLI.class));
    }

    @Test
    public void config() {
        Game testGame = new Game();
        testGame.config();
        assertEquals(100, testGame.getBoard().getTotalPositions());
    }
}