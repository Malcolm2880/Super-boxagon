package ui;

import java.util.Timer;
import java.util.TimerTask;

import model.*;

import java.util.Scanner;


// Note that the GameApp structure is based on the TellerApp project
//this class runs the whole program. It interacts with the other classes to deliver the full user experience.
public class GameApp {
    private Scanner input;
    private Leaderboard board;
    private int difficulty = 1;
    private boolean timeOut = false;
    private Player player;
    private int currentScore;


    // EFFECTS: Starts the program
    public GameApp() {
        startRunning();
    }


    // REQUIRES: User input a movement value that is an integer.
// MODIFIES: this
    // EFFECTS: allows you to actually play the game.
    private void runGame() {
        boolean alive = true;
        resetGame();
        while (alive) {
            EnemyPattern enemy = new EnemyPattern(difficulty);
            System.out.println(enemy.getPattern());
            System.out.println(player.generatePosition(difficulty));
            Timer t = new Timer();
            t.schedule(setupTimer(player), 10 * 1000);
            player.setIndex(input.nextInt());
            if (timeOut) {
                System.out.println("Your input was too late");
                t.cancel();
                break;
            }
            t.cancel();
            if (player.getIndex() > difficulty || player.getIndex() < 0 || player.isDead(enemy)) {
                System.out.println("You died!");
                break;
            }
            currentScore = currentScore + difficultyIncrease(currentScore);
        }
        difficulty = 1;

        createScore(currentScore);
    }
    // MODIFIES: this
    // EFFECTS: resets game variables back to default

    private void resetGame() {
        currentScore = 0;
        player = new Player();
        timeOut = false;
    }

    //MODIFIES: this
    //EFFECTS: Increases the difficulty at incremental rates.
    private int difficultyIncrease(int score) {
        if (score % 3 == 0) {
            difficulty++;
        }
        return 1;
    }
    // MODIFIES: this
    // EFFECTS: creates a new score with the given value

    private void createScore(int score) {
        System.out.println("Give your score a name:");
        board.addScore(new Score(input.next(), score));

    }

    // MODIFIES: this
    // EFFECTS: setups the timer
    private TimerTask setupTimer(Player p) {


        TimerTask tt = new TimerTask() {
            public void run() {
                timeOut = true;
            }
        };
        return tt;

    }

    // taken from the teller class example
    //EFFECTS: processes user command
    private void startRunning() {
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


    // silghtly modified from the teller method with the same name
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("h")) {
            showLeaderBoard();
        } else if (command.equals("d")) {
            changeDifficulty();
        } else if (command.equals("t")) {
            showTopScore();
        } else if (command.equals("s")) {
            runGame();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // taken from the teller class
    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        board = new Leaderboard();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //Taken from the teller class
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\th -> Highscores");
        System.out.println("\tt -> Your Top Score");
        System.out.println("\td -> Change Difficulty");
        System.out.println("\ts -> Start the game");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays all the scores
    private void showLeaderBoard() {

        System.out.println(board.getAllScores());

    }

    // EFFECTS: displays the top score with the given name
    private void showTopScore() {
        System.out.println("Please enter your name");
        int index = board.getNamesScore(input.next());
        if (index > 0) {
            System.out.println("Your highest score is at position: " + index);
        } else {
            System.out.println("No one has that name!");
        }
    }

    //Some of the code structure taken from the teller class
    // MODIFIES: this
    // EFFECTS: changes the difficulty
    private void changeDifficulty() {
        System.out.println("Type I if you want to increase the difficulty");
        System.out.println("Type L if you want to lower the difficulty");
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("i")) {
            System.out.println("Your difficulty was increased by one, Good luck!");
            System.out.println("Note that your difficulty will be reset on death");

            difficulty++;
        } else if (command.equals("l")) {
            if (difficulty == 1) {
                System.out.println("It's as low as it can go!");
            } else {
                System.out.println("Your difficulty was lowered by one");
                difficulty--;
            }

        } else {
            System.out.println("Invalid Command!");

        }



    }

}
