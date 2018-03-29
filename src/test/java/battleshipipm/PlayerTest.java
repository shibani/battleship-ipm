package battleshipipm;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlayerTest {

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        final Player testPlayer = new Player("Player1");
        final Field field = testPlayer.getClass().getDeclaredField("name");
        field.setAccessible(true);

        assertEquals("Player1", field.get(testPlayer));
    }

    @Test
    public void getBoard() throws NoSuchFieldException, IllegalAccessException {
        final Player testPlayer = new Player("Player1");

        assertThat(testPlayer.getBoard(), instanceOf(Board.class));
    }
}