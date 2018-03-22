package battleshipipm;
import java.util.ArrayList;

public class BoardCLI {

    private Board board;
    private String[] alpha = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
    private String colHeaders =  "\n      0     1     2     3     4     5     6     7     8     9    ";
    private String horizontalDivider = "\n   +=====+=====+=====+=====+=====+=====+=====+=====+=====+=====+ ";
    private String firstAlpha = " A | ";
    private String verticalDivider = " | ";
    private String singleSpace = " ";

    BoardCLI(Board board){
        this.board = board;
    }

    public void print(Board board){
        this.printHeader();

        ArrayList<String> positions = board.getPositions();
        int i = 0;
        while(i < board.getTotalPositions()) {
            this.printCellLeft();
            this.printCellContent(positions, i);
            this.printCellRight(i);
            i++;
        }
    }

    public void displayShips(Board board){
        this.printHeader();

        ArrayList<String> positions = board.getPositions();
        int i = 0;
        while(i < board.getTotalPositions()) {
            this.printCellLeft();
            this.printShipContent(positions, i);
            this.printCellRight(i);
            i++;
        }
    }

    private void printHeader(){
        System.out.print(colHeaders);
        System.out.println(horizontalDivider);
        System.out.print(firstAlpha);
    }

    private void printCellLeft(){
        System.out.print(singleSpace);
    }

    private void printCellRight(int i){
        System.out.print(singleSpace + verticalDivider);
        if(i % board.getRowSize() == (board.getRowSize() - 1)){
            System.out.println(horizontalDivider);
            if(i != board.getTotalPositions() - 1){
                System.out.print(singleSpace);
                System.out.print(alpha[(i/board.getRowSize()) + 1]);
                System.out.print(verticalDivider);
            }
        }
    }

    private void printCellContent(ArrayList<String> positions, int i){
        System.out.print(positions.get(i));
    }

    private void printShipContent(ArrayList<String> positions, int i){
        if(board.getFilledPositions().contains(i)){
            int index = board.getFilledPositions().indexOf(i);
            System.out.print(board.getShipMarkers().get(index));
        } else {
            System.out.print(positions.get(i));
        }
    }
}
