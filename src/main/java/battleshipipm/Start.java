package battleshipipm;

import java.io.IOException;

public class Start {

    public static void main(String[] args) {

        CLI cli = new CLI();
        Game game = new Game();
        App app = new App(cli, game);

        //App.start();
        app.playGame();
    }
}
