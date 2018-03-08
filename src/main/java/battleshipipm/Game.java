package battleshipipm;

import java.io.IOException;

public class Game {

    private CLI cli;

    Game(CLI cli){
        this.cli = cli;
    }

    public void start() throws IOException {

        this.cli.welcome();

        this.cli.getPlayerName();

        this.cli.getPlayerNameInput();
    }
}
