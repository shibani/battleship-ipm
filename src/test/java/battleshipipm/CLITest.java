package battleshipipm;

import org.jetbrains.annotations.NotNull;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.internal.Utils;

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

    @Test
    public void getPlayerNameInputWhenInputIsEmptyString() {
        byte[] data = "".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI cli = new CLI();
        String playerInput = cli.getPlayerNameInput();
        assertEquals("", playerInput);
    }

    @Test
    public void nameIOExceptionRetriesUntilItGetsValidInput() {
        byte[] data = "test player".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI cli = new CLI();
        MockBufferedReader br = new MockBufferedReader(cli.inputStreamReader);
        cli.br = br;
        String playerInput = cli.getPlayerNameInput();
        assertEquals(1, br.exceptionCount);
        assertEquals("test player", playerInput);
    }

    @Test
    public void parsePlayerNameInputReturnsAStringIfValid() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        String str = "Player 1";
        CLI cli = new CLI();
        cli.parsePlayerNameInput("Player 1");

        bo.flush();
        String inputLines = new String(bo.toByteArray());
        assertTrue(inputLines.contains("You selected Player 1"));
    }

    @Test
    public void parsePlayerNameInputChecksIfArgIsEmptyString() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        String str = "";
        CLI cli = new CLI();

        cli.parsePlayerNameInput(str);

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Name cannot be empty. Please try again."));
    }

    @Test
    public void getPlayerNameCallsAskForPlayerName(){
        CLI Cli = new CLI();
        CLI spyCli = Mockito.spy(Cli);

        doNothing().when(spyCli).askForPlayerName();
        doReturn("Player 1").when(spyCli).getPlayerNameInput();
        doReturn("Player 1").when(spyCli).parsePlayerNameInput("Player 1");
        spyCli.getPlayerName();

        verify(spyCli, atLeast(1)).askForPlayerName();
    }

    @Test
    public void getPlayerNameCallsGetPlayerNameInput(){
        CLI Cli = new CLI();
        CLI spyCli = Mockito.spy(Cli);

        doNothing().when(spyCli).askForPlayerName();
        doReturn("Player 1").when(spyCli).getPlayerNameInput();
        doReturn("Player 1").when(spyCli).parsePlayerNameInput("Player 1");

        spyCli.getPlayerName();

        verify(spyCli, times(1)).getPlayerNameInput();
    }

    @Test
    public void getPlayerNameCallsParsePlayerNameInput(){
        CLI Cli = new CLI();
        CLI spyCli = Mockito.spy(Cli);

        byte[] data = "Player 1".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        doNothing().when(spyCli).askForPlayerName();
        doReturn("Player 1").when(spyCli).getPlayerNameInput();
        doReturn("Player 1").when(spyCli).parsePlayerNameInput("Player 1");
        spyCli.getPlayerName();

        verify(spyCli, times(1)).parsePlayerNameInput(anyString());
    }

    @Test
    public void getPlayerNameCallsLoopAgainIfInputIsEmptyString(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerName();
        doReturn("").doReturn("test player").when(spyCli).getPlayerNameInput();
        doReturn("").doReturn("test player").when(spyCli).parsePlayerNameInput(anyString());

        spyCli.getPlayerName();

        verify(spyCli, atLeast(2)).parsePlayerNameInput(anyString());
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
    public void parsePlayerMoveInputReturnsAStringIfValid() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        String str = "h3";
        CLI cli = new CLI();
        cli.parsePlayerMoveInput(str, "Player1");

        bo.flush();
        String inputLines = new String(bo.toByteArray());
        assertTrue(inputLines.contains("You selected"));
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void parsePlayerMoveInputCanSendExit() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        String str = "exit";
        CLI cli = new CLI();

        exit.expectSystemExitWithStatus(0);
        cli.parsePlayerMoveInput(str, "Player1");

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Player1, thanks for playing!"));
    }

    @Test
    public void parsePlayerMoveInputChecksIfArgIsEmptyString() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        String str = "";
        CLI cli = new CLI();

        cli.parsePlayerMoveInput(str, "Player1");

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Move must be one letter for the row and one number for the column."));
    }

    @Test
    public void getPlayerMoveCallsAskForPlayerMove(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(1)).askForPlayerMove(anyString(), anyString());
    }

    @Test
    public void getPlayerMoveCallsGetPlayerMoveInput(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(1)).getPlayerMoveInput();
    }

    @Test
    public void getPlayerMoveCallsParsePlayerMoveInput(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(1)).parsePlayerMoveInput(anyString(), anyString());
    }

    @Test
    public void getPlayerMoveCallsLoopAgainIfInputIsEmptyString(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("").doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("").doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(2)).parsePlayerMoveInput(anyString(), anyString());
    }

    @Test
    public void getPlayerMoveCallsLoopAgainIfInputIsLongerThan2Characters(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("").doReturn("335").doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("").doReturn("335").doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(3)).parsePlayerMoveInput(anyString(), anyString());
    }

    @Test
    public void getPlayerMoveCallsLoopAgainIfFirstCharacterOfInputIsNotALetter(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("").doReturn("33").doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("").doReturn("33").doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(3)).parsePlayerMoveInput(anyString(), anyString());
    }

    @Test
    public void getPlayerMoveCallsLoopAgainIfSecondCharacterOfInputIsNotANumber(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("").doReturn("dd").doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("").doReturn("dd").doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(3)).parsePlayerMoveInput(anyString(), anyString());
    }

    @Test
    public void getPlayerMoveCallsLoopAgainIfFirstCharacterisLargerThanAlphaBound(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("").doReturn("t5").doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("").doReturn("t5").doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(3)).parsePlayerMoveInput(anyString(), anyString());
    }

    @Test
    public void getPlayerMoveCallsLoopAgainIfSecondCharacterisLargerThanNumericBound(){
        CLI testCli = new CLI();
        CLI spyCli = Mockito.spy(testCli);

        doNothing().when(spyCli).askForPlayerMove(anyString(), anyString());
        doReturn("").doReturn("e55").doReturn("c3").when(spyCli).getPlayerMoveInput();
        doReturn("").doReturn("e55").doReturn("c3").when(spyCli).parsePlayerMoveInput(anyString(), anyString());

        spyCli.getPlayerMove(anyString());

        verify(spyCli, atLeast(3)).parsePlayerMoveInput(anyString(), anyString());
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