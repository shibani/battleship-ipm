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
        String moveString = this.cli.getPlayerMove(player.getName());
        int move = this.game.convertPlayerMoveToInt(moveString);
        if(this.game.validMove(move)){
            this.game.makeMove(move);
            this.game.getBoardCli().print(this.game.getBoard());
        } else {
            this.gameLoop(player);
        }
    }

    public void gameOver(){

    }
}
