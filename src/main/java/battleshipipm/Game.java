package battleshipipm;

public class Game {

    private BoardCLI boardCli;
    private Human human;
    private Computer computer;
    private Player currentPlayer;
    private String mode;

    Game(){
        this.boardCli = null;
        this.mode = "normal";
    }

    public void config(String playerName){

        this.setHuman(playerName);
        this.setComputer();
        this.setCurrentPlayer();

        this.setBoardCli();
    }

    public void setBoardCli(){
        this.boardCli = new BoardCLI();
    }

    public BoardCLI getBoardCli() {
        return boardCli;
    }

    public void setHuman(String name){
        this.human = new Human(name);
    }

    public Human getHuman(){
        return this.human;
    }

    public void setComputer(){
        this.computer = new Computer("Computer");
    }

    public Computer getComputer(){
        return this.computer;
    }

    public void setMode(String str){
        if(str.equals("off")){
            this.mode = "normal";
        } else if (str.equals("dev")) {
            this.mode = "dev";
        }
    }

    public String getMode(){
        return this.mode;
    }

    public void setCurrentPlayer(){
        if(this.currentPlayer == this.getHuman()){
            this.currentPlayer = this.getComputer();
        }else if(this.currentPlayer == this.getComputer()){
            this.currentPlayer = this.getHuman();
        }else if(this.currentPlayer == null){
            this.currentPlayer = this.getHuman();
        }
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public Board getCurrentBoard(){
        return this.getCurrentPlayer().getBoard();
    }

    public void printBoard(){
        this.getBoardCli().printBoard(this.getCurrentBoard(), this.getMode());
    }

    public void printBoard(Board board, String str){
        this.getBoardCli().printBoard(board, str);
    }

    public boolean validMove(int position) {
        return (this.getCurrentBoard().isEmpty(position));
    }

    public int convertPlayerMoveToInt(String move){
        return this.getCurrentBoard().coordsToPosition(move);
    }

    public void makeMove(int move){
        this.getCurrentBoard().addMarker(move);
    }

    public void printStatus(String name, int position){
        String status = this.getCurrentBoard().checkForHit(position);
        if(status.equals("hit")){
            String shipType = this.getCurrentBoard().shipIsSunk(position);
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
        return this.getCurrentBoard().allShipsAreSunk();
    }

    /*public Board getPlayerBoard(){
        return this.getHuman().getBoard();
    }

    public Board getOpponentBoard(){
        return this.getOpponent().getBoard();
    }*/
}
