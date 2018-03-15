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

        Player player = app.setup();
        app.gameLoop(player);
        //app.gameOver();
    }

    public Player setup() throws IOException {
        String playerName = this.cli.setup();
        return this.game.config(playerName);
    }

    public void gameLoop(Player player) throws IOException {
        String move = this.cli.getPlayerMove(player.getName());
        //convert move to coords
        //if this.game.validMove(move);
        //String status = this.game.playTurn(move);
        //this.cli.moveStatus(status);
    }

    public void gameOver(){

    }
}
