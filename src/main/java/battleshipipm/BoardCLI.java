package battleshipipm;
import java.util.ArrayList;

public class BoardCLI {

    private Board board;
    private String[] alpha = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
    private String colHeaders =  "      0     1     2     3     4     5     6     7     8     9    \n";
    private String horizontalDivider = "   +=====+=====+=====+=====+=====+=====+=====+=====+=====+=====+ \n";
    private String firstAlpha = " A | ";
    private String verticalDivider = " | ";
    private String singleSpace = " ";
    private String newLine = "\n";

    BoardCLI(Board board){
        this.board = board;
    }

    public void print(Board board){
        ArrayList<String> positions = board.getPositions();

        System.out.print(colHeaders);
        System.out.println(horizontalDivider);
        System.out.print(firstAlpha);

        int i = 0;
        while(i < board.getTotalPositions()) {
            System.out.print(singleSpace);
            System.out.print(positions.get(i));
            System.out.print(singleSpace + verticalDivider);
            if(i % board.getRowSize() == (board.getRowSize() - 1)){
                System.out.print(newLine);
                System.out.println(horizontalDivider);
                if(i != board.getTotalPositions() - 1){
                    System.out.print(singleSpace);
                    System.out.print(alpha[(i/board.getRowSize()) + 1]);
                    System.out.print(verticalDivider);
                }
            }
            i++;
        }
    }

    public void displayShips(Board board){
        ArrayList<String> positions = board.getPositions();

        System.out.print(colHeaders);
        System.out.print(horizontalDivider);
        System.out.print(firstAlpha);

        int i = 0;
        while(i < board.getTotalPositions()) {
            System.out.print(singleSpace);
            if(board.getFilledPositions().contains(i)){
                int index = board.getFilledPositions().indexOf(i);
                System.out.print(board.getShipMarkers().get(index));
            } else {
                System.out.print(positions.get(i));
            }
            System.out.print(singleSpace + verticalDivider);
            if(i % board.getRowSize() == board.getRowSize() - 1){
                System.out.print(newLine);
                System.out.print(horizontalDivider);
                if(i != board.getTotalPositions() - 1){
                    System.out.print(singleSpace);
                    System.out.print(alpha[(i/board.getRowSize()) + 1]);
                    System.out.print(verticalDivider);
                }
            }
            i++;
        }
    }
}
