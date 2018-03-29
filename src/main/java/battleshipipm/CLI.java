package battleshipipm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {
    public InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    public BufferedReader br = new BufferedReader(inputStreamReader);
    private Game game;

    private String welcomeString =
                    "==============================\n" +
                    "    Welcome to Battleship     \n" +
                    "==============================\n";

    private String getPlayerNameString = "Enter your Human Name:\n" +
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
        try {
            String input = br.readLine();
            if (input != null) {
                return input.trim();
            } else {
                return "";
            }
        } catch(IOException e) {
            return getPlayerMoveInput();
        }
    }

    public String parsePlayerNameInput(String str){
        String output;
        if(str.trim().length() > 1){
            output = str.trim();
            this.printString("You selected " + output.trim() + "\n");
        } else {
            output = "";
            this.printString("Name cannot be empty. Please try again.\n");
        }
        return output;
    }

    public String getPlayerMoveInput() {
        try {
            String input = br.readLine();
            if (input != null) {
                return input.trim();
            } else {
                return "";
            }
        } catch(IOException e) {
            return getPlayerMoveInput();
        }
    }

    public String parsePlayerMoveInput(String str, String name){
        String output;
        if ((str.length() == 2)
                && Character.isLetter(str.charAt(0))
                && Character.isDigit(str.charAt(1))
                && (str.charAt(0) <= 'j')
                && (str.charAt(1) <= '9')) {
            output = str;
            this.printString("You selected " + output + ".\n");
        } else if (str.equals("exit")) {
            output = "";
            this.printString(name + ", thanks for playing!");
            System.exit(0);
        } else if (str.equals("dev")) {
            this.printString("Now entering developer mode\n");
            this.getGame().printBoard(getGame().getCurrentBoard(), str);
            this.getGame().setMode(str);
            output = "";
        } else if (str.equals("off")) {
            this.printString("Switching off developer mode\n");
            this.getGame().setMode(str);
            output = "";
        } else {
            this.printString("Move must be one letter for the row and one number for the column. Please try again.\n");
            output = "";
        }
        return output;
    }

    public String getPlayerName(){
        String result;
        boolean invalid = true;
        do {
            this.askForPlayerName();
            String input = this.getPlayerNameInput();
            result = this.parsePlayerNameInput(input);
            if(result.trim().length() > 1){
                invalid = false;
            }
        } while (invalid);

        return result;
    }

    public String getPlayerMove(String playerName) {
        String result;
        boolean invalid = true;
        do {
            this.askForPlayerMove(this.getPlayerMoveString, playerName);
            String str = this.getPlayerMoveInput();
            result = this.parsePlayerMoveInput(str, playerName);
            if(result.trim().length() > 1){
                if ((str.length() == 2)
                        && Character.isLetter(str.charAt(0))
                        && Character.isDigit(str.charAt(1))
                        && (str.charAt(0) <= 'j')
                        && (str.charAt(1) <= '9')){
                    invalid = false;
                }
            }
        } while (invalid);

        return result;
    }

    public void tryAgain(){
        this.printString(this.tryAgainString);
    }

    public void endGame(Human human){
        this.printString(gameOver + ", " + human.getName() + "!");
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}