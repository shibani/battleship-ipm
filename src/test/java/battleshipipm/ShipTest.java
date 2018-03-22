package battleshipipm;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void setType() throws NoSuchFieldException, IllegalAccessException {
        final Ship ship = new Ship();
        ship.setType("Destroyer");
        final Field field = ship.getClass().getDeclaredField("type");
        field.setAccessible(true);

        assertEquals("Destroyer", field.get(ship));
    }

    @Test
    public void getType() throws NoSuchFieldException, IllegalAccessException {
        final Ship ship = new Ship();
        final Field field = ship.getClass().getDeclaredField("type");
        field.setAccessible(true);
        field.set(ship, "Submarine");

        final String result = ship.getType();

        assertEquals("Submarine", result);
    }

    @Test
    public void setSize() throws NoSuchFieldException, IllegalAccessException {
        final Ship ship = new Ship();
        ship.setSize(5);
        final Field field = ship.getClass().getDeclaredField("size");
        field.setAccessible(true);

        assertEquals(5, field.get(ship));
    }

    @Test
    public void getSize() throws NoSuchFieldException, IllegalAccessException {
        final Ship ship = new Ship();
        final Field field = ship.getClass().getDeclaredField("size");
        field.setAccessible(true);
        field.set(ship, 3);

        final int result = ship.getSize();

        assertEquals(3, result);
    }

    @Test
    public void setPosition() throws NoSuchFieldException, IllegalAccessException {
        final Ship ship = new Ship();

        Integer[] positions = new Integer[]{33, 34, 35, 36, 37};
        final List<Integer> positionsToList = Arrays.asList(positions);

        ship.setPosition(positions);
        final Field field = ship.getClass().getDeclaredField("position");
        field.setAccessible(true);

        assertEquals(positionsToList, field.get(ship));
    }

    @Test
    public void getPosition() throws NoSuchFieldException, IllegalAccessException {
        final Ship ship = new Ship();
        final Field field = ship.getClass().getDeclaredField("position");
        field.setAccessible(true);

        final List<Integer> testPosition = Arrays.asList(27,28,29);
        field.set(ship, testPosition);
        final List<Integer> result = ship.getPosition();

        assertEquals(testPosition, result);
    }
}