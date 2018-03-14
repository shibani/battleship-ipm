package battleshipipm;

import java.io.IOException;

public class Game {

    private Board board;
    private BoardCLI boardCli;

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

    public void config(){
        this.setBoard(100);
        this.setBoardCli(this.getBoard());
        this.board.setShips();
        //this.getBoardCli().print(this.getBoard());
        this.getBoardCli().displayShips(this.getBoard());
    }
}
