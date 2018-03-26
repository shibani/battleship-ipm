package battleshipipm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Start {

    public static void main(String[] args) throws IOException {

        CLI cli = new CLI();
        Game game = new Game();
        App app = new App(cli, game);

        app.playGame();
    }
}
