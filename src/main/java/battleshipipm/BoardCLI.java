package battleshipipm;
import java.util.ArrayList;

public class BoardCLI {

    public void print(Board board){
        ArrayList<String> positions = board.getPositions();

        int i = 0;

        String[] alpha = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        System.out.println("      0     1     2     3     4     5     6     7     8     9    ");

        String border = "   +=====+=====+=====+=====+=====+=====+=====+=====+=====+=====+ ";

        System.out.println(border);
        System.out.print(" A | ");

        while(i < board.getTotalPositions()) {
            System.out.print(" ");
            System.out.print(positions.get(i));
            System.out.print("  | ");
            if(i % 10 == 9){
                System.out.print("\n");
                System.out.println(border);
                if(i != board.getTotalPositions() - 1){
                    System.out.print(" " + (alpha[(i/10) + 1]) + " | ");
                }
            }
            i++;
        }
    }

    public void displayShips(Board board){
        ArrayList<String> positions = board.getPositions();

        int i = 0;

        String[] alpha = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        System.out.println("Display Ships!");

        System.out.println("      0     1     2     3     4     5     6     7     8     9    ");

        String border = "   +=====+=====+=====+=====+=====+=====+=====+=====+=====+=====+ ";

        System.out.println(border);
        System.out.print(" A | ");

        while(i < board.getTotalPositions()) {
            System.out.print(" ");
            if(board.getFilledPositions().contains(i)){
                System.out.print("S");
            } else {
                System.out.print(positions.get(i));
            }
            System.out.print("  | ");
            if(i % 10 == 9){
                System.out.print("\n");
                System.out.println(border);
                if(i != board.getTotalPositions() - 1){
                    System.out.print(" " + (alpha[(i/10) + 1]) + " | ");
                }
            }
            i++;
        }
    }
}
