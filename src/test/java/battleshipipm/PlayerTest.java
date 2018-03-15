package battleshipipm;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        final Player testPlayer = new Player("Player1");
        final Field field = testPlayer.getClass().getDeclaredField("name");
        field.setAccessible(true);

        assertEquals("Player1", field.get(testPlayer));
    }
}