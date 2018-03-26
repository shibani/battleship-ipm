package battleshipipm;

import org.jetbrains.annotations.NotNull;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.io.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class CLITest {

    @Test
    public void welcome() throws IOException {

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI testCLI = new CLI();
        testCLI.welcome();

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Welcome to Battleship"));
    }

    @Test
    public void askForPlayerName() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI testCLI = new CLI();
        testCLI.askForPlayerName();

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Enter your Player Name:"));
    }

    @Test
    public void getPlayerNameInput() {
        byte[] data = "Player 1".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI testCLI = new CLI();
        String testResult = testCLI.getPlayerNameInput();

        assertEquals("Player 1", testResult);
    }

    @Test(expected = NullPointerException.class)
    public void getPlayerNameInput1() {
        byte[] data = "  ".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI testCLI = new CLI();
        String testResult = testCLI.getPlayerNameInput();

        assertEquals("Player 1", testResult);
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

   /* @Test
    public void getPlayerMove() {
        when(mockedCli.getPlayerMove("testPlayer")).thenCallRealMethod();
        mockedCli.getPlayerMove("testPlayer");

        verify(mockedCli, times(1)).askForPlayerMove(null, "testPlayer");
    } */

    /*@Test
    public void getPlayerMove1() {
        doNothing().when(mockedCli).askForPlayerMove("test", "testPlayer");
        when(mockedCli.getPlayerMoveInput("test", "testPlayer")).thenReturn("3");
        when(mockedCli.getPlayerMove("testPlayer")).thenCallRealMethod();
        mockedCli.getPlayerMove("testPlayer");

        verify(mockedCli, times(1)).getPlayerMoveInput(null, "testPlayer");
    }*/

    @Test
    public void askForPlayerMove() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI testCLI = new CLI();
        testCLI.askForPlayerMove(" test", "testPlayer");

        bo.flush();
        String inputLines = new String(bo.toByteArray());
        assertTrue(inputLines.contains("testPlayer test"));
    }

    @Test
    public void getPlayerMoveInput(){
        byte[] data = "f5".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI testCLI = new CLI();
        String testResult = testCLI.getPlayerMoveInput();

        assertEquals("f5", testResult);
    }

    /*@Test
    public void getPlayerMoveInput() {
        byte[] data = "f5".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI testCLI = new CLI();
        String testResult = testCLI.getPlayerMoveInput("f5", "Player1");

        assertEquals("f5", testResult);
    }

    @Test(expected = NullPointerException.class)
    public void getPlayerMoveInput1() {
        byte[] data = "  ".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI testCLI = new CLI();
        String testResult = testCLI.getPlayerMoveInput(anyString(), anyString());
    }*/

    /*@Test(expected = IOException.class)
    public void getPlayerMoveInput2() throws IOException {
        InputStream input = mock(InputStream.class);
        when(input.read()).thenThrow(new IOException());
        System.setIn(input);

        CLI testCLI = new CLI();
        testCLI.getPlayerMoveInput(anyString(), anyString());
    }*/

    // @Rule
    // public ExpectedException thrown= ExpectedException.none();

    /*@Test
    public void getPlayerMoveInput3() {
        byte[] data = "t5".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI testCLI = new CLI();

        thrown.expect(NullPointerException.class);
        testCLI.getPlayerMoveInput(anyString(), anyString());
    }*/

    @Test
    public void emptyStringReturnsEmptyString() {
        byte[] data = "".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI cli = new CLI();
        String playerInput = cli.getPlayerMoveInput();
        assertEquals("", playerInput);
    }

    @Test
    public void stringInputReturnsStringValue() {
        byte[] data = "hello world".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI cli = new CLI();
        String playerInput = cli.getPlayerMoveInput();
        assertEquals("hello world", playerInput);
    }

    @Test
    public void IOExceptionRetriesUntilItGetsValidInput() {
        byte[] data = "hello world".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI cli = new CLI();
        MockBufferedReader br = new MockBufferedReader(cli.inputStreamReader);
        cli.br = br;
        String playerInput = cli.getPlayerMoveInput();
        assertEquals(1, br.exceptionCount);
        assertEquals("hello world", playerInput);
    }

    @Test
    public void tryAgain() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI testCLI = new CLI();
        testCLI.tryAgain();

        bo.flush();
        String inputLines = new String(bo.toByteArray());
        assertTrue(inputLines.contains("That position is already filled. Please try again."));
    }

    @Test
    public void endGame() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        Player testPlayer = new Player("Player 2");
        CLI testCLI = new CLI();
        testCLI.endGame(testPlayer);

        bo.flush();
        String inputLines = new String(bo.toByteArray());
        assertTrue(inputLines.contains("You won, Player 2!"));
    }
}