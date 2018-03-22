package battleshipipm;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void getTotalPositions() {
        Board testBoard = new Board();

        testBoard.setTotalPositions(10);
        assertEquals(10, testBoard.getTotalPositions());
    }

    @Test
    public void getPositions() {
        Board testBoard = new Board();

        testBoard.setTotalPositions(10);
        testBoard.setPositions();

        assertEquals(" ", testBoard.getPositions().get(9));
    }
}