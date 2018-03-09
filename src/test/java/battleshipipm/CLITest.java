package battleshipipm;

import org.junit.Test;
import java.io.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;


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
    public void getPlayerName() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI testCLI = new CLI();
        testCLI.getPlayerName("Enter your Player Name:");

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
    public void getPlayerNameInput1() throws IOException {

        byte[] data = "".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI testCLI = new CLI();
        String testResult = testCLI.getPlayerNameInput("test");

        assertEquals("", testResult);
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

    /*@Test
    public void setup() throws IOException {
        byte[] data = "test".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        CLI mockedCli = mock(CLI.class);
        App mockedApp = mock(App.class);

        mockedApp.setup();
        //String testResult = testCLI.getPlayerNameInput("another test");

        verify(mockedCli).setup();
    }*/

    /*@Test
    public void setup1() throws IOException {
        CLI mockedCLI = mock(CLI.class);

        mockedCLI.setup();
        verify(mockedCLI, times(1)).getPlayerName("test");
    }*/
}