package battleshipipm;

import java.io.IOException;

public class Game {

    private Board board;
    private BoardCLI boardCli;

    Game(Board board, BoardCLI boardCli){
        this.board = board;
        this.boardCli = boardCli;
    }

    public Board getBoard() {
        return board;
    }

    public BoardCLI getBoardCli() {
        return boardCli;
    }
}
