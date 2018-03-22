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

    public boolean validMove(int position) {
        return (this.getBoard().isEmpty(position));
    }

    public void makeMove(int move){
        this.getBoard().addMarker(move);
    }

    public int convertPlayerMoveToInt(String move){
        return this.getBoard().coordsToPosition(move);
    }

    public void printBoard(){
        this.getBoardCli().print(this.getBoard());
    }

    public void printStatus(String name, int position){
        String status = this.getBoard().checkForHit(position);
        if(status.equals("hit")){
            String shipType = this.getBoard().shipIsSunk(position);
            if(shipType.equals("none")){
                this.getBoardCli().printStatus(name, status);
            } else {
                this.getBoardCli().printSunkStatus(name, shipType);
            }
        } else {
            this.getBoardCli().printStatus(name, status);
        }
    }

    public boolean isOver(){
        //return this.getBoard().isFull();
        return this.getBoard().allShipsAreSunk();
    }
}
