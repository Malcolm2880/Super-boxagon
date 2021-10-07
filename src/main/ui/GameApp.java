package ui;

import model.*;

import java.util.Scanner;

public class GameApp {
    private Scanner input;
    private Leaderboard board;
    private int difficulty = 1;


    public GameApp() {

        while (true) {

        }
        //stub
    }
    // Note that the GameApp structure is based on the TellerApp project

    private void runGame() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("h")) {
            showHighScore();
        } else if (command.equals("d")) {
            changeDifficulty();
        } else if (command.equals("s")) {
            runGame();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        board = new Leaderboard();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\th -> Highscores");
        System.out.println("\td -> Change Difficulty");
        System.out.println("\ts -> Start the game");
        System.out.println("\tq -> quit");
    }


    private void showHighScore() {

        System.out.println(board.getAllScores());

    }





    // MODIFIES: this
    // EFFECTS: changes the difficulty
    private void changeDifficulty() {

        difficulty++;


    }

}
