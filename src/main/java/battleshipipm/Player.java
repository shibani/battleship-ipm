package battleshipipm;

public class Player {

    private String name;
    private Board board;

    Player(String name){
        this.name = name;
        this.setBoard(100);
    }

    public String getName(){
        return this.name;
    }

    private void setBoard(int size){
        this.board = new Board();
        this.board.setTotalPositions(size);
        this.board.setPositions();
        this.board.setShips();
    }

    public Board getBoard() {
        return board;
    }
}
