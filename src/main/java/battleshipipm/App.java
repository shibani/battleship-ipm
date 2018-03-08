package battleshipipm;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        CLI cli = new CLI();
        Game game = new Game(cli);
        game.start();
    }
}
