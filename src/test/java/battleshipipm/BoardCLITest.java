package battleshipipm;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class BoardCLITest {

    @Test
    public void print() throws IOException {
        Board board = new Board();
        BoardCLI bcli = new BoardCLI(board);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        bcli.print(board);

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains(" +=====+=====+=====+=====+=====+=====+=====+=====+=====+=====+ "));
    }

    @Test
    public void displayShips() throws IOException {
        Board board = new Board();
        BoardCLI bcli = new BoardCLI(board);

        board.setTotalPositions(100);
        board.setPositions();
        board.setShips();

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        bcli.displayShips(board);

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("|  P  |"));
        assertTrue(inputLines.contains("|  A  |"));
        assertTrue(inputLines.contains("|  C  |"));
        assertTrue(inputLines.contains("|  D  |"));
        assertTrue(inputLines.contains("|  S  |"));
    }

    @Test
    public void printStatus() throws IOException {
        Board board = new Board();
        BoardCLI bcli = new BoardCLI(board);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        bcli.printStatus("Player1", "hit");

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Player1, that was a hit!"));
    }

    @Test
    public void printSunkStatus() throws IOException {
        Board board = new Board();
        BoardCLI bcli = new BoardCLI(board);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        bcli.printSunkStatus("Player1", "Patrol Boat");

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Player1, you sunk the Patrol Boat!"));
    }

    @Test
    public void printSunkStatusReturnsShipType() throws IOException {
        Board board = new Board();
        BoardCLI bcli = new BoardCLI(board);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        bcli.printSunkStatus("Player1", "Patrol Boat");

        bo.flush();
        String inputLines = new String(bo.toByteArray());

        assertTrue(inputLines.contains("Player1, you sunk the Patrol Boat!"));
    }
}