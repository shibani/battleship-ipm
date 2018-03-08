import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {

    public static void start(){
        System.out.println("==============================");
        System.out.println("    " + "Welcome to Battleship");
        System.out.println("==============================\n");
    }

    public static void requestPlayerName(){
        System.out.println("Enter your Player Name:");
        System.out.println("(Name should contain only numbers and alphabets. Name should not be empty)");
    }

    public static String getPlayerNameInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String output;

        try {
            output = input.trim();
        } catch(NullPointerException e) {
            output = "Name cannot be blank please try again.";
            requestPlayerName();
        }
        return output;
    }
}
