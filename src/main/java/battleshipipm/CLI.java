package battleshipipm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CLI {

     private String welcomeString =
            "==============================\n" +
            "    Welcome to Battleship     \n" +
            "==============================\n";

    private String getPlayerNameString = "Enter your Player Name:\n" +
            "(Name should contain only numbers and alphabets. Name should not be empty)\n";

    private String getPlayerMoveString = ", enter your move with one letter for the row and one digit for the column, e.g 'd9':\n";

    private String tryAgainString = "That position is already filled. Please try again.\n";


    public String setup() throws IOException {
        this.welcome(this.welcomeString);
        this.askForPlayerName(this.getPlayerNameString);
        return this.getPlayerNameInput(this.getPlayerNameString);
    }

    public void welcome(String str){
        this.printString(str);
    }

    public void askForPlayerName(String str) {
        this.printString(str);
    }

    public void askForPlayerMove(String str, String name){
        this.printString(name + str);
    }

    public void printString(String message) {
        System.out.print(message);
    }

    public String getPlayerNameInput(String str) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String output = "";

        try {
            output = input.trim();
            if (output.length() < 1){
                System.out.print("Name cannot be empty. Please try again.\n");
                this.askForPlayerName(str);
                this.getPlayerNameInput(str);
            } else {
                System.out.print("You selected " + output + "\n");
            }
        } catch(NullPointerException e) {
            System.out.print("Caught NullPointerException");
        }

        /*Scanner sc = new Scanner(System.in);
        String output = "";
        try{
            output = sc.next().trim();
            sc.nextLine();
            if (output.length() > 1){
                System.out.print("You selected " + output + "\n");
            } else {
                System.out.print("Name cannot be empty. Please try again.\n");
                this.askForPlayerName(str);
                this.getPlayerNameInput(str);
            }
        } catch (NullPointerException e){
            System.out.print("Caught NullPointerException");
        }
        System.out.println("output to send: " + output);
        sc.close();*/
        //System.out.println("output to send: " + output);
        return output;
    }

    public String getPlayerMoveInput(String str, String name) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String output = "";
        try {
            output = input.trim();
            if (output.length() > 1){
                System.out.print("You selected " + output + "\n");
            } else {
                System.out.print("Move cannot be empty. Please try again.\n");
                this.askForPlayerMove(str, name);
                this.getPlayerMoveInput(str, name);
            }
        } catch(NullPointerException e) {
            System.out.print("Caught NullPointerException");
        }
        return output;
    }

    public String getPlayerMove(String playerName) throws IOException {
        this.askForPlayerMove(this.getPlayerMoveString, playerName);
        return this.getPlayerMoveInput(this.getPlayerMoveString, playerName);
    }

    public void tryAgain(){
        this.printString(this.tryAgainString);
    }
}
