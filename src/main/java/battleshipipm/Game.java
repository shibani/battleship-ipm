package battleshipipm;

public class Game {

    private BoardCLI boardCli;
    private Player player;
    private Player opponent;
    private Player currentPlayer;
    private String mode;

    Game(){
        this.boardCli = null;
        this.mode = "normal";
    }

    public void config(String playerName){

        this.setPlayer(playerName);
        this.setOpponent();
        this.setCurrentPlayer();

        this.setBoardCli();
        this.printBoard(this.getCurrentBoard(), this.getMode());
    }

    public void setBoardCli(){
        this.boardCli = new BoardCLI();
    }

    public BoardCLI getBoardCli() {
        return boardCli;
    }

    public void setPlayer(String name){
        this.player = new Player(name);
    }

    public Player getPlayer(){
        return this.player;
    }

    public void setOpponent(){
        this.opponent = new Player("Computer");
    }

    public Player getOpponent(){
        return this.opponent;
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
        if(this.currentPlayer == this.getPlayer()){
            this.currentPlayer = this.getOpponent();
        }else if(this.currentPlayer == this.getOpponent()){
            this.currentPlayer = this.getPlayer();
        }else if(this.currentPlayer == null){
            this.currentPlayer = this.getPlayer();
        }
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
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
        return this.getPlayer().getBoard();
    }

    public Board getOpponentBoard(){
        return this.getOpponent().getBoard();
    }*/
}
