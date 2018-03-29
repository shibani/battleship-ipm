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
        this.setup();

        while(!this.gameOver()){
            this.gameLoop(this.getCurrentPlayer());
            this.game.setCurrentPlayer();
        }
        if(this.gameOver()){
            this.endGame(this.getPlayer());
        }
    }

    public void setup() {
        this.cli.welcome();
        this.cli.setGame(game);
        String playerName = this.cli.getPlayerName();
        this.game.config(playerName);
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

    public Player getPlayer(){
        return this.game.getPlayer();
    }

    public Player getCurrentPlayer(){
        return this.game.getCurrentPlayer();
    }
}