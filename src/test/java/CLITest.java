import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CLITest {

    @Test
    public void start() throws IOException {

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI.start();

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Welcome to Battleship"));
    }

    @Test
    public void requestPlayerName() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        CLI.requestPlayerName();

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Enter your Player Name:\n(Name should contain only numbers and alphabets. Name should not be empty)"));
    }

    @Test
    public void getPlayerNameInput() throws IOException {

        byte[] data = "Test".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        String testResult = CLI.getPlayerNameInput();

        assertEquals("Test", testResult);
    }

    @Test
    public void getPlayerNameInput1() throws IOException {

        byte[] data = "".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        System.setIn(input);

        String testResult = CLI.getPlayerNameInput();

        assertEquals("Name cannot be blank please try again.", testResult);
    }
}