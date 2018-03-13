package battleshipipm;

import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void setTotalPositions() throws IllegalAccessException, NoSuchFieldException {
        final Board board = new Board();
        board.setTotalPositions(10);
        final Field field = board.getClass().getDeclaredField("totalPositions");
        field.setAccessible(true);

        assertEquals(10, field.get(board));
    }

    @Test
    public void getTotalPositions() throws NoSuchFieldException, IllegalAccessException {
        final Board board = new Board();
        final Field field = board.getClass().getDeclaredField("totalPositions");
        field.setAccessible(true);
        field.set(board, 10);

        final int result = board.getTotalPositions();

        assertEquals(10, result);
    }

    @Test
    public void setPositions() throws NoSuchFieldException, IllegalAccessException {
        final Board board = new Board();
        board.setTotalPositions(5);

        board.setPositions();
        final Field field = board.getClass().getDeclaredField("positions");
        field.setAccessible(true);

        final List<String> testPositions = Arrays.asList(" ", " ", " ", " ", " ");

        assertEquals(testPositions, field.get(board));
    }

    @Test
    public void getPositions() {
        Board testBoard = new Board();

        testBoard.setTotalPositions(5);
        testBoard.setPositions();

        final List<String> testPositions = Arrays.asList(" ", " ", " ", " ", " ");

        assertEquals(testPositions, testBoard.getPositions());
    }

    @Test
    public void setShips() throws NoSuchFieldException, IllegalAccessException {
        final Board board = new Board();
        board.setTotalPositions(100);

        board.setPositions();
        board.setShips();
        final Field field = board.getClass().getDeclaredField("ships");
        field.setAccessible(true);

        final List<Ship> ships = board.getShips();

        assertEquals(ships, field.get(board));
    }

    @Test
    public void getShips() throws IllegalAccessException, NoSuchFieldException {
        final Board board = new Board();
        final Field field = board.getClass().getDeclaredField("ships");
        field.setAccessible(true);
        final List<Ship> ships = new ArrayList<>();
        ships.add(new Ship());
        ships.add(new Ship());
        field.set(board, ships);

        final List<Ship> result = board.getShips();

        assertEquals(ships, result);
    }

    @Test
    public void getFilledPositions() {
        final Board board = new Board();
        board.setTotalPositions(100);
        board.setShips();

        assertEquals(17, board.getFilledPositions().size());
    }
}