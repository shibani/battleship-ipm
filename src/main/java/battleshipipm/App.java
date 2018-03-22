package battleshipipm;

import java.io.IOException;

public class App {

    private CLI cli;
    private Game game;

    App(CLI cli, Game game){
        this.cli = cli;
        this.game = game;
    }

    public static void start() throws IOException {
        CLI cli = new CLI();
        Game game = new Game();
        App app = new App(cli, game);

        app.setup();
    }

    public void setup() throws IOException {
        this.cli.setup();
        this.game.config();
    }
}
