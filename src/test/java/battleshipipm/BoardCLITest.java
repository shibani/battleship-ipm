package battleshipipm;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class BoardCLITest {

    @Test
    public void printBoard() throws IOException {
        Board board = new Board();
        BoardCLI bcli = new BoardCLI();
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        bcli.printBoard(board);

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains(" +=====+=====+=====+=====+=====+=====+=====+=====+=====+=====+ "));
    }
}