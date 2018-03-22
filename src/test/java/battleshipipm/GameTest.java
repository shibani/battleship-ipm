package battleshipipm;

import battleshipipm.CLI;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameTest {

    @Test
    public void start() throws IOException {

        CLI mockedCLI = mock(CLI.class);
        Game testGame = new Game(mockedCLI);

        testGame.start();
        verify(mockedCLI).welcome();
    }

    @Test
    public void start1() throws IOException {

        CLI mockedCLI = mock(CLI.class);
        Game testGame = new Game(mockedCLI);

        testGame.start();
        mockedCLI.welcome();

        verify(mockedCLI).getPlayerName();
    }

    @Test
    public void start2() throws IOException {

        CLI mockedCLI = mock(CLI.class);
        Game testGame = new Game(mockedCLI);

        testGame.start();
        mockedCLI.welcome();
        mockedCLI.getPlayerName();

        verify(mockedCLI).getPlayerNameInput();
    }

}