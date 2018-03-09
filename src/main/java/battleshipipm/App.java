package battleshipipm;

import java.io.IOException;

public class App {

    private CLI cli;
    private BoardCLI boardCli;
    private Game game;

    App(CLI cli, Game game){
        this.cli = cli;
        this.game = game;
    }

    public static void start() throws IOException {

        CLI cli = new CLI();
        BoardCLI boardCli = new BoardCLI();
        Board board = new Board();
        Game game = new Game(board, boardCli);
        App app = new App(cli, game);

        app.setup();
    }

    public void setup() throws IOException {
        this.cli.setup();
        this.game.getBoardCli().printBoard(game.getBoard());
    }
}
