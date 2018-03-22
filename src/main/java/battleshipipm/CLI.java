package battleshipipm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {

    public void welcome(){
        System.out.println("==============================");
        System.out.println("    " + "Welcome to Battleship");
        System.out.println("==============================\n");
    }

    public void getPlayerName() throws IOException {
        System.out.println("Enter your Player Name:");
        System.out.println("(Name should contain only numbers and alphabets. Name should not be empty)");
    }

    public String getPlayerNameInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String output = "";

        try {
            output = input.trim();
            if (output.length() > 1){
                System.out.print("You selected " + output);
            } else {
                System.out.print("Name cannot be empty. Please try again.\n");
                getPlayerName();
                getPlayerNameInput();
            }
        } catch(NullPointerException e) {
            System.out.print("Caught NullPointerException");
        }
        return output;
    }
}
