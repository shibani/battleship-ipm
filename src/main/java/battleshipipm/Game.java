package battleshipipm;

import java.io.IOException;

public class Game {

    private Board board;
    private BoardCLI boardCli;

    Game(){
        this.board = null;
        this.boardCli = null;
    }

    private void setBoard(int size){
        this.board = new Board();
        this.board.setTotalPositions(size);
        this.board.setPositions();
    }

    public Board getBoard() {
        return board;
    }

    private void setBoardCli(){
        this.boardCli = new BoardCLI();
    }

    public BoardCLI getBoardCli() {
        return boardCli;
    }

    public void config(){
        this.setBoard(100);
        this.setBoardCli();
        this.getBoardCli().print(this.getBoard());
    }
}
