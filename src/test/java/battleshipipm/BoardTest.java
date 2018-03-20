package battleshipipm;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.anyOf;
import static net.bytebuddy.matcher.ElementMatchers.is;
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
    public void getFilledShipPositions() {
        final Board board = new Board();
        board.setTotalPositions(100);
        board.setShips();

        assertEquals(17, board.getFilledShipPositions().size());
    }

    @Test
    public void getShipMarkers() {
        final Board board = new Board();
        board.setTotalPositions(100);
        board.setShips();

        assertEquals(17, board.getShipMarkers().size());
    }

    @Test
    public void setRowSize() throws NoSuchFieldException, IllegalAccessException {
        final Board board = new Board();
        board.setTotalPositions(100);
        final Field field = board.getClass().getDeclaredField("rowSize");
        field.setAccessible(true);
        board.setRowSize();

        assertEquals(10, field.get(board));
    }

    @Test
    public void getRowSize() {
        final Board board = new Board();
        board.setTotalPositions(100);
        board.setRowSize();

        assertEquals(10, board.getRowSize());
    }

    @Test
    public void coordsToPosition() {
        String testCoords = "g5";
        final Board testBoard = new Board();
        int result = testBoard.coordsToPosition(testCoords);
        final String testCoordsAlpha = Character.toString(testCoords.charAt(0)).toUpperCase();
        final int testCoordsInt = Character.getNumericValue(testCoords.charAt(testCoords.length() - 1));
        int alphaIndex = Arrays.asList(BoardCLI.alpha).indexOf(testCoordsAlpha);
        int coordsInt = (alphaIndex * 10) + testCoordsInt;

        assertEquals(coordsInt, result);
    }

    @Test
    public void isEmpty() {
        String testCoords = "g5";
        final Board testBoard = new Board();
        testBoard.setTotalPositions(100);
        testBoard.setPositions();
        int testPosition = testBoard.coordsToPosition(testCoords);
        boolean result = testBoard.isEmpty(testPosition);

        assertEquals(true, result);
    }

    @Test
    public void addMarker() {
        final Board testBoard = new Board();
        testBoard.setTotalPositions(100);
        testBoard.setPositions();
        testBoard.addMarker(30);

        String testString = testBoard.getPositions().get(30);
        assertTrue("X".equals(testString) || "n".equals(testString));
    }

    @Test
    public void isEmpty1() {
        final Board testBoard = new Board();
        testBoard.setTotalPositions(100);
        testBoard.setPositions();
        testBoard.addMarker(35);

        boolean result = testBoard.isEmpty(35);

        assertEquals(false, result);
    }

    @Test
    public void checkForHit() {
        final Board testBoard = new Board();
        testBoard.setTotalPositions(100);
        testBoard.setPositions();
        testBoard.setShips();
        int position = testBoard.getFilledShipPositions().get(0);
        if(testBoard.isEmpty(position)){
            testBoard.addMarker(position);
        }

        String result = testBoard.checkForHit(position);
        assertEquals("hit", result);
    }

    @Test
    public void isFull() {
        final Board testBoard = new Board();
        testBoard.setTotalPositions(5);
        testBoard.setPositions();
        testBoard.addMarker(0);
        testBoard.addMarker(1);
        testBoard.addMarker(2);
        testBoard.addMarker(3);
        testBoard.addMarker(4);

        assertTrue(testBoard.isFull());
    }

    @Test
    public void allShipsAreSunk() {
        final Board testBoard = new Board();
        testBoard.setTotalPositions(100);
        testBoard.setPositions();
        testBoard.setShips();

        assertFalse(testBoard.allShipsAreSunk());
    }

    @Test
    public void allShipsAreSunk1() {
        final Board testBoard = new Board();
        testBoard.setTotalPositions(100);
        testBoard.setPositions();
        testBoard.setShips();

        for(Integer shipPosition: testBoard.getFilledShipPositions()){
            testBoard.getPositions().set(shipPosition, "X");
        }
        assertTrue(testBoard.allShipsAreSunk());
    }
}