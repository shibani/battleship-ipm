package battleshipipm;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class ComputerTest {
    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        final Computer testComputer = new Computer("Computer");
        final Field field = testComputer.getClass().getDeclaredField("name");
        field.setAccessible(true);

        assertEquals("Computer", field.get(testComputer));
    }

    @Test
    public void getBoard() {
        final Computer testComputer = new Computer("Computer");

        assertThat(testComputer.getBoard(), instanceOf(Board.class));
    }

    @Test
    public void setType() throws NoSuchFieldException, IllegalAccessException {
        final Computer testComputer = new Computer("Computer");
        final String testType = "Computer";
        final Field field = testComputer.getClass().getDeclaredField("type");
        field.setAccessible(true);
        field.set(testComputer, testType);

        assertEquals("Computer", field.get(testComputer));
    }

    @Test
    public void getType() {
        final Computer testComputer = new Computer("Computer");
        testComputer.setType("Computer");

        assertEquals("Computer", testComputer.getType());
    }

    @Test
    public void getMove(){
        final Computer testComputer = new Computer("Computer");
        int result = testComputer.getMove();

        assertTrue(0 <= result && result <= 100);
    }
}