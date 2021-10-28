package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;

// This class references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
//whenever the teller project is mentioned, this is what is being referred to.

//Note that the file reading and writing code is adapted from the following repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Whenever the "JsonDemo" repo is mentioned, this is what is being referred to.


// Note that the GameApp structure is based on the TellerApp project
//this class runs the whole program. It interacts with the other classes to deliver the full user experience.
public class GameApp {
    private Scanner input;

    private Leaderboard board;
    private int difficulty = 1;
    private boolean timeOut = false;
    private Player player;
    private int currentScore;

    private static final String JSON_STORE = "./data/MyProject.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    private static final String JSON_STORE_D = "./data/MyDifficultyTest.json";
    private JsonWriter jsonWriterD;
    private JsonReader jsonReaderD;


    // EFFECTS: Starts the program
    public GameApp() throws FileNotFoundException {
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
        }  else if (command.equals("l")) {
            loadGameApp();
        } else if (command.equals("a")) {
            saveGameApp();
        }  else if (command.equals("o")) {
            saveDifficulty();
        } else if (command.equals("p")) {
            loadDifficulty();
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
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriterD = new JsonWriter(JSON_STORE_D);
        jsonReaderD = new JsonReader(JSON_STORE_D);
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
        System.out.println("\tl -> Load Highscore");
        System.out.println("\ta -> Save Highscore");
        System.out.println("\tp -> Load Difficulty");
        System.out.println("\to -> Save Difficulty");
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


    // Adapted from the JsonDemo project
    //MODIFIES: this
    // EFFECTS: saves the workroom to file

    private void saveGameApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(board);
            jsonWriter.close();
            System.out.println("Saved " + /*workRoom.getName()*/ "Leaderboard " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //Adapted from the JsonDemo project
    //MODIFIES: this
    //EFFECTS: saves the difficulty to a file

    private void saveDifficulty() {
        try {
            jsonWriterD.open();
            jsonWriterD.write(difficulty);
            jsonWriterD.close();
            System.out.println("Saved " +  "Difficulty " + " to " + JSON_STORE_D);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_D);
        }
    }

    //Adapted from the JsonDemo project
    // MODIFIES: this
    // EFFECTS: loads difficulty from file
    private void loadDifficulty() {
        try {
            difficulty = jsonReaderD.readInt();
            System.out.println("Loaded " +  "Difficulty " + " from " + JSON_STORE_D);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_D);
        }
    }


    //Adapted from the Json
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadGameApp() {
        try {
            board = jsonReader.read();
            System.out.println("Loaded " + /*workRoom.getName()*/ "Leaderboard " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
