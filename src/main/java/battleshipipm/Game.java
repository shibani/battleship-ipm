package battleshipipm;

import java.io.IOException;

public class Game {

    private Board board;
    private BoardCLI boardCli;
    private Player player;

    Game(){
        this.board = null;
        this.boardCli = null;
    }

    public void setBoard(int size){
        this.board = new Board();
        this.board.setTotalPositions(size);
        this.board.setPositions();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoardCli(Board board){
        this.boardCli = new BoardCLI(board);
    }

    public BoardCLI getBoardCli() {
        return boardCli;
    }

    public Player config(String playerName){
        this.setBoard(100);
        this.setBoardCli(this.getBoard());
        this.getBoard().setShips();
        this.getBoardCli().print(this.getBoard());
        //this.getBoardCli().displayShips(this.getBoard());
        this.setPlayer(playerName);
        return this.getPlayer();
    }

    public void setPlayer(String name){
        this.player = new Player(name);
    }

    public Player getPlayer(){
        return this.player;
    }

    public int convertCoordsToMove(){
        //int moveToInt = App.cli.coordsToPosition(move);
        return 0;
    }

    public void validMove(){
        //check if spot is available
        //int moveToBoard = App.game.getCurrentPlayer().potentialMove(moveToInt); - validation
    }

    public void playTurn(){
        // check if move is a hit or a miss
        // mark the board
    }
}
