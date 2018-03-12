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

    @Mock
    private CLI mockedCli;
    private Game mockedGame;
    private Board mockedBoard;

    @InjectMocks
    private App mockedApp;

    @Before
    public void beforeSetup() {
        initMocks(this);
    }

    @Ignore
    public void start() throws IOException {
        mockedApp.start();
        verify(mockedApp, times(1)).start();
    }

    @Ignore
    public void setup() throws IOException {
        when(mockedCli.setup()).thenReturn("testPlayer");
        doNothing().when(mockedGame).config();
        mockedGame.config();

        verify(mockedCli, times(1)).setup();
    }

    @Ignore
    public void setup2() throws IOException {
        doNothing().when(mockedGame).config();
        mockedApp.setup();
        mockedCli.setup();

        verify(mockedGame, times(1)).config();
    }
}



