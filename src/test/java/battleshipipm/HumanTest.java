package battleshipipm;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class HumanTest {

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        final Human testHuman = new Human("Player1");
        final Field field = testHuman.getClass().getDeclaredField("name");
        field.setAccessible(true);

        assertEquals("Player1", field.get(testHuman));
    }

    @Test
    public void getBoard() {
        final Human testHuman = new Human("Player1");

        assertThat(testHuman.getBoard(), instanceOf(Board.class));
    }

    @Test
    public void setType() throws NoSuchFieldException, IllegalAccessException {
        final Human testHuman = new Human("Player1");
        final String testType = "Human";
        final Field field = testHuman.getClass().getDeclaredField("type");
        field.setAccessible(true);
        field.set(testHuman, testType);

        assertEquals("Human", field.get(testHuman));
    }

    @Test
    public void getType() {
        final Human testHuman = new Human("Player1");
        testHuman.setType("Human");

        assertEquals("Human", testHuman.getType());
    }
}