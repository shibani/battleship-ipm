package battleshipipm;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.io.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class CLITest {

    @Test
    public void welcome() throws IOException {

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI testCLI = new CLI();
        testCLI.welcome("Welcome to Battleship");

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Welcome to Battleship"));
    }

    @Test
    public void askForPlayerName() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI testCLI = new CLI();
        testCLI.askForPlayerName("Enter your Player Name:");

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Enter your Player Name:"));
    }

    @Test
    public void getPlayerNameInput() throws IOException {

        byte[] data = "Test".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI testCLI = new CLI();
        String testResult = testCLI.getPlayerNameInput("test");

        assertEquals("Test", testResult);
    }

    @Test
    public void printString() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        String str = "test";
        CLI testCLI = new CLI();
        testCLI.printString(str);

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("test"));
    }

    @Mock
    private Player mockedPlayer;

    @Mock
    private CLI mockedCli;

    @Before
    public void beforeSetup() {
        initMocks(this);
    }

    @Test
    public void setup() throws IOException {
        when(mockedCli.setup()).thenReturn("testPlayer");
        assertEquals("testPlayer", mockedCli.setup());
    }

    @Test
    public void getPlayerMove() throws IOException {
        when(mockedCli.getPlayerMove("testPlayer")).thenCallRealMethod();
        mockedCli.getPlayerMove("testPlayer");

        verify(mockedCli, times(1)).askForPlayerMove(null, "testPlayer");
    }

    @Test
    public void getPlayerMove1() throws IOException {
        doNothing().when(mockedCli).askForPlayerMove("test", "testPlayer");
        when(mockedCli.getPlayerMoveInput("test", "testPlayer")).thenReturn("3");
        when(mockedCli.getPlayerMove("testPlayer")).thenCallRealMethod();
        mockedCli.getPlayerMove("testPlayer");

        verify(mockedCli, times(1)).getPlayerMoveInput(null, "testPlayer√");
    }

    @Test
    public void askForPlayerMove() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI testCLI = new CLI();
        Player player = new Player("testPlayer");
        testCLI.askForPlayerMove("test", player.getName());

        bo.flush();
        String inputLines = new String(bo.toByteArray());
        assertTrue(inputLines.contains("testPlayer test"));
    }

    @Test
    public void getPlayerMoveInput() {

    }
}