package battleshipipm;

import java.io.IOException;

public class App {

    private CLI cli;
    private Game game;

    App(CLI cli, Game game){
        this.cli = cli;
        this.game = game;
    }

    public void playGame() {
        Player player = this.setup();

        while(!this.gameOver()){
            this.gameLoop(player);
        }
        if(this.gameOver()){
            this.endGame(player);
        }
    }

    public Player setup() {
        String playerName = this.cli.setup();
        return this.game.config(playerName);
    }

    public void gameLoop(Player player) {
        String moveString = this.cli.getPlayerMove(player.getName());
        int move = this.game.convertPlayerMoveToInt(moveString);
        if(this.game.validMove(move)){
            this.game.makeMove(move);
            this.game.printBoard();
            this.game.printStatus(player.getName(), move);
        } else {
            this.cli.tryAgain();
            this.gameLoop(player);
        }
    }

    public boolean gameOver(){
        return this.game.isOver();
    }

    public void endGame(Player player){
        this.cli.endGame(player);
    }
}