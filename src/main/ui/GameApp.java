package ui;

import java.util.Timer;
import java.util.TimerTask;
import model.*;

import java.util.Scanner;



// Note that the GameApp structure is based on the TellerApp project

public class GameApp {
    private Scanner input;
    private Leaderboard board;
    private int difficulty = 1;
    private boolean timeOut = false;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    // REQUIRES: User input a movement value that is an integer.
// MODIFIES: this
    // EFFECTS: allows you to actually play the game.
    public void runGame() {
        boolean alive = true;
        int score = 0;
        Player p = new Player();
        int index = p.getIndex();
        while (alive) {
            EnemyPattern enemy = new EnemyPattern(difficulty);
            System.out.println(enemy.getPattern());
            System.out.println(p.generatePosition(difficulty));
            Timer t = new Timer();
            t.schedule(setupTimer(p),10 * 1000);
            index = input.nextInt();
            if (timeOut) {
                System.out.println("Your input was too late");
                t.cancel();
                break;
            }
            t.cancel();
            if (index > difficulty + 1) {
                System.out.println("Invalid, ur ded kiddo!");
                break;
            }
            p.setIndex(index);

            if (p.isDead(enemy)) {
                System.out.println("You died!");
                break;
            }
            score++;
            if (score % 3 == 0) {
                difficulty++;
            }

        }
        resetGame();
        createScore(score);
    }
    // MODIFIES: this
    // EFFECTS: resets game variables back to default

    public void resetGame() {
        timeOut = false;
        difficulty = 1;
    }
    // MODIFIES: this
    // EFFECTS: creates a new score with the given value

    public void createScore(int score) {
        System.out.println("Give your score a name:");
        board.addScore(new Score(input.next(), score));

    }

    // MODIFIES: this
    // EFFECTS: setups the timer
    public TimerTask setupTimer(Player p) {


        TimerTask tt = new TimerTask() {
            public void run() {
                timeOut = true;
            }
        };
        return tt;

    }



    // EFFECTS: processes user command
    public GameApp() {
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
            showLeaderBoard();
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

    // EFFECTS: displays all the scores
    private void showLeaderBoard() {

        System.out.println(board.getAllScores());

    }


    // MODIFIES: this
    // EFFECTS: changes the difficulty
    private void changeDifficulty() {
        System.out.println("Your difficulty was increased by one, Good luck!");
        difficulty++;


    }

}
