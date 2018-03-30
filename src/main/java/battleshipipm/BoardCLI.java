package battleshipipm;
import java.util.ArrayList;

public class BoardCLI {

    private String colHeaders =  "\n      0     1     2     3     4     5     6     7     8     9    ";
    private String horizontalDivider = "\n   +=====+=====+=====+=====+=====+=====+=====+=====+=====+=====+ ";
    private String verticalDivider = " | ";
    private String singleSpace = " ";
    private String newLine = "\n";
    private String firstAlpha = String.format("%s%s%s", singleSpace, alpha[0], verticalDivider);
    public static final String[] alpha = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};


    public void printBoard(Board board, String mode){
        this.printHeader();

        ArrayList<String> positions = board.getPositions();
        int i = 0;
        while(i < board.getTotalPositions()) {
            this.printCellLeft();
            if(mode.equals("dev")){
                if(board.getPositions().get(i).equals("X")){
                    this.printCellContent(positions, i);
                } else if (board.getFilledShipPositions().contains(i)) {
                    int index = board.getFilledShipPositions().indexOf(i);
                    System.out.print(board.getShipMarkers().get(index));
                } else {
                    System.out.print(board.getPositions().get(i));
                }
            } else {
                this.printCellContent(positions, i);
            }
            this.printCellRight(board, i);
            i++;
        }
    }

    public void printStatus(String name, String status){
        System.out.print(String.format("%s%s%s%s", name, ", that was a ", status, "!\n"));
    }

    public void printSunkStatus(String name, String shipType){
        System.out.print(String.format("%s%s%s%s", name, ", you sunk the ", shipType, "!\n"));
    }

    public void displayShips(Board board){
        this.printHeader();

        ArrayList<String> positions = board.getPositions();
        int i = 0;
        while(i < board.getTotalPositions()) {
            this.printCellLeft();
            this.printShipContent(board, positions, i);
            this.printCellRight(board, i);
            i++;
        }
    }

    private void printHeader(){
        System.out.print(String.format("%s%s%s%s", colHeaders, horizontalDivider, newLine, firstAlpha));
    }

    private void printCellLeft(){
        System.out.print(singleSpace);
    }

    private void printCellRight(Board board, int i){
        System.out.print(String.format("%s%s", singleSpace, verticalDivider));
        if(i % board.getRowSize() == (board.getRowSize() - 1)){
            System.out.println(horizontalDivider);
            if(i != board.getTotalPositions() - 1){
                System.out.print(String.format("%s%s%s", singleSpace, alpha[(i/board.getRowSize()) + 1], verticalDivider));
            }
        }
    }

    private void printCellContent(ArrayList<String> positions, int i){
        System.out.print(positions.get(i));
    }

    private void printShipContent(Board board, ArrayList<String> positions, int i){
        if(board.getFilledShipPositions().contains(i)){
            int index = board.getFilledShipPositions().indexOf(i);
            System.out.print(board.getShipMarkers().get(index));
        } else {
            System.out.print(positions.get(i));
        }
    }
}