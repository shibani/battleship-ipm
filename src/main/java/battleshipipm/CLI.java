package battleshipipm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {

     private String welcome =
            "==============================\n" +
            "    Welcome to Battleship     \n" +
            "==============================\n";

    private String getPlayerName = "Enter your Player Name:\n" +
            "(Name should contain only numbers and alphabets. Name should not be empty)\n";


    public void setup() throws IOException {
        welcome(this.welcome);
        try {
            getPlayerName(this.getPlayerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getPlayerNameInput(getPlayerName);
    }

    public void welcome(String str){
        this.printString(str);
    }

    public void printString(String message) {
        System.out.print(message);
    }

    public void getPlayerName(String str) throws IOException {
        this.printString(str);
    }

    public String getPlayerNameInput(String str) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String output = "";

        try {
            output = input.trim();
            if (output.length() > 1){
                System.out.print("You selected " + output);
            } else {
                System.out.print("Name cannot be empty. Please try again.\n");
                getPlayerName(str);
                getPlayerNameInput(getPlayerName);
            }
        } catch(NullPointerException e) {
            System.out.print("Caught NullPointerException");
        }
        return output;
    }
}
