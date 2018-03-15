package battleshipipm;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AppTest {

    @Ignore
    public void start() throws IOException {
        Game testGame = mock(Game.class);
        CLI testCli = mock(CLI.class);
        //App testApp = mock(App.class);

        App testApp = new App(testCli, testGame);
        App.start();

        verify(testApp, times(1)).setup();
    }

    @Test
    public void setup() throws IOException {
        Game testGame = mock(Game.class);
        CLI testCli = mock(CLI.class);

        App testApp = new App(testCli, testGame);

        testApp.setup();
        verify(testCli, times(1)).setup();
    }

    @Test
    public void setup2() throws IOException {
        Game testGame = mock(Game.class);
        CLI testCli = mock(CLI.class);

        App testApp = new App(testCli, testGame);
        when(testCli.setup()).thenReturn("Player1");
        testApp.setup();

        verify(testGame, times(1)).config(any(String.class));
    }

    @Test
    public void gameLoop() {
    }

    @Test
    public void gameOver() {
    }
}



