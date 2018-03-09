package battleshipipm;

import java.util.ArrayList;

public class BoardCLI {

    public void printBoard(Board board){

        ArrayList<String> positions = board.getPositions();

        int i = 0;

        String[] alpha = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" }; ;

        System.out.println("      0     1     2     3     4     5     6     7     8     9    ");

        String border = "   +=====+=====+=====+=====+=====+=====+=====+=====+=====+=====+ ";

        System.out.println(border);
        System.out.print(" A | ");

        while(i < board.getTotalPositions()) {
            System.out.print((" " + positions.get(i) + " " + " | "));
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
