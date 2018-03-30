package battleshipipm;

public class Human extends Player {

    private String name;
    private Board board;
    private String type;


    Human(String name){
        this.name = name;
        this.setBoard(100);
    }

    private void setBoard(int size){
        this.board = new Board();
        this.board.setTotalPositions(size);
        this.board.setPositions();
        this.board.setShips();
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setType(String type){
        this.type = type;
    }

    @Override
    public String getType(){
        return this.type;
    }
}
