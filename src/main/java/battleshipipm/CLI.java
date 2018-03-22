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

    private String gameOver = "You won";

    public void welcome(){
        this.printString(this.welcomeString);
    }

    public void askForPlayerName() {
        this.printString(this.getPlayerNameString);
    }

    public void askForPlayerMove(String str, String name){
        this.printString(name + str);
    }

    public void printString(String message) {
        System.out.print(message);
    }

    public String getPlayerNameInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String errorMessage = "Please enter a valid name: ";
        String str = this.getPlayerNameString;
        boolean invalid = true;
        String output = null;

        do {
            try {
                output = (br.readLine());
                if (output.trim().length() > 1) {
                    System.out.print("You selected " + output.trim() + "\n");
                    invalid = false;
                } else {
                    System.out.print("Name cannot be empty. Please try again.\n");
                    this.askForPlayerName();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        } while(invalid);

        return output;
    }

    public String getPlayerMoveInput(String str, String name) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String errorMessage = "Please enter a valid move: ";
        String output = null;
        boolean invalid = true;

        do{
            try{
                output = br.readLine();
                if(output.trim().length() > 1){
                    if((output.trim().length() == 2) && Character.isLetter(output.trim().charAt(0))
                            && Character.isDigit(output.trim().charAt(1)) && (output.trim().charAt(0) <= 'j')){
                        System.out.print("You selected " + output.trim() + "\n");
                        invalid = false;
                    } else if (output.trim().equals("exit")){
                        System.out.print(name + ", thanks for playing!");
                        System.exit(0);
                    } else {
                        System.out.print("Move must be one letter for the row and one number for the column. Please try again.\n");
                        this.askForPlayerMove(str, name);
                    }
                } else {
                    System.out.print("Move cannot be empty. Please try again.\n");
                    this.askForPlayerMove(str, name);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        } while(invalid);

        return output;
    }

    public String getPlayerMove(String playerName) {
        this.askForPlayerMove(this.getPlayerMoveString, playerName);
        return this.getPlayerMoveInput(this.getPlayerMoveString, playerName);
    }

    public void tryAgain(){
        this.printString(this.tryAgainString);
    }

    public void endGame(Player player){
        this.printString(gameOver + ", " + player.getName() + "!");
    }
}