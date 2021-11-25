package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.*;

import javax.imageio.ImageIO;
import javax.swing.*;

// This class references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
//whenever the teller project is mentioned, this is what is being referred to.

//Note that the file reading and writing code is adapted from the following repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Whenever the "JsonDemo" repo is mentioned, this is what is being referred to.


// Note that the GameApp structure is based on the TellerApp project
//this class runs the whole program. It interacts with the other classes to deliver the full user experience.

//This class also references the simple drawing project

public class GameApp extends JFrame {
    private Scanner input;


    BufferedImage image;
    ArrayList<Tool> tools;


    JPanel boardArea;
    JPanel imageArea;
    private Leaderboard board;
    private int difficulty = 1;
    private Player player;
    private int currentScore;

    private static final String JSON_STORE = "./data/MyProject.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    private static final String JSON_STORE_D = "./data/MyDifficultyTest.json";
    private JsonWriter jsonWriterD;
    private JsonReader jsonReaderD;


    //EFFECTS: opens an option panel and accepts a name
    public String createName() {
        String name = JOptionPane.showInputDialog("enter a name");
        return name;
    }

    //REQUIRES: input to be an integer
    //EFFECTS: opens an option panel and accepts a number
    public int createPoints() {
        String score = JOptionPane.showInputDialog("enter the number of points");
        int s = Integer.parseInt(score);
        return s;
    }


    //structure taken from the simple drawing project
    //MODIFIES: this
    //EFFECTS: initiallizes all the required buttons
    private void createButtons() {


        JPanel toolArea = new JPanel();

        tools = new ArrayList<>();
        toolArea.setLayout(new GridLayout(0, 1));
        toolArea.setSize(new Dimension(10, 10));
        add(toolArea, BorderLayout.SOUTH);


        StartGameButton gameButton = new StartGameButton(this, toolArea);
        tools.add(gameButton);

        TopScoreButton topScore = new TopScoreButton(this, toolArea);
        tools.add(topScore);

        LoadFileButton loadFile = new LoadFileButton(this, toolArea);
        tools.add(loadFile);

        SaveFileButton saveFile = new SaveFileButton(this, toolArea);
        tools.add(saveFile);

        AddScoreButton addScore = new AddScoreButton(this, toolArea);
        tools.add(addScore);


    }

    //structure taken from the simple drawing project
    //MODIFIES: this
    //EFFECTS:  Displays the leaderboard
    public void displayLeaderboard() {

        if (boardArea != null) {
            this.remove(boardArea);
        }

        boardArea = new JPanel();
        boardArea.setLayout(new GridLayout(0, 1));
        boardArea.setPreferredSize(new Dimension(200, 100));
        imageArea = new JPanel();
        imageArea.setLayout(new GridLayout(0, 1));
        imageArea.setPreferredSize(new Dimension(200, 10));

        add(boardArea, BorderLayout.WEST);
        add(imageArea, BorderLayout.EAST);
        JLabel title = new JLabel("The Current Leaderboard:");
        boardArea.add(title);

        for (Score s : board.getNames()) {
            JLabel topScore2 = new JLabel(s.getName() + " " + s.getScore());
            boardArea.add(topScore2);
        }
        boardArea.revalidate();
        add(boardArea, BorderLayout.WEST);


    }

    //MODIFIES: this
    //EFFECTS: initializes all the visual objects
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(1000, 700));
        createButtons();
        displayLeaderboard();

        try {
            image = ImageIO.read(new File("./data/Number1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        //the following code is adapted from https://stackoverflow.com/questions/32051657/how-to-perform-action-after-jframe-is-closed
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EventLog el = EventLog.getInstance();

                for (Event next : el) {
                    System.out.println(next.getDate() + " " + next.getDescription());
                }
            }
        });


    }


    // EFFECTS: Starts the program
    public GameApp() throws FileNotFoundException {
        startRunning();
    }


    // REQUIRES: User input a movement value that is an integer.
// MODIFIES: this
    // EFFECTS: allows you to actually play the game.
    public void runGame() {
        boolean alive = true;
        resetGame();
        while (alive) {
            EnemyPattern enemy = new EnemyPattern(difficulty);
            String index = JOptionPane.showInputDialog(enemy.getPattern());
            try {
                player.setIndex(Integer.parseInt(index));
            } catch (Exception e) {
                break;
            }
            if (player.getIndex() > difficulty || player.getIndex() < 0 || player.isDead(enemy)) {
                //death
                break;
            }
            currentScore = currentScore + difficultyIncrease(currentScore);
        }
        difficulty = 1;

        String name = createName();
        createScore(name, currentScore);
        if (board.getNamesScore(name) == 1) {

            drawPicture();
        }
    }

    //adapted from the PhotoAlbum Project
    //MODIFIES: this
    //EFFECTS: draws the number 1 image
    private void drawPicture() {

        JLabel pic = new JLabel(new ImageIcon(image));
        imageArea.add(pic);
        imageArea.revalidate();
    }


    // MODIFIES: this
    // EFFECTS: resets game variables back to default

    private void resetGame() {
        currentScore = 0;
        player = new Player();
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
    // EFFECTS: creates a new score with the given values
    public void createScore(String name, int score) {
        board.addScore(new Score(name, score));
        displayLeaderboard();

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
        } else if (command.equals("l")) {
            loadGameApp();
        } else if (command.equals("a")) {
            saveGameApp();
        } else if (command.equals("o")) {
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
        initializeGraphics();

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
    public void showTopScore() {
        System.out.println("Please enter your name");
        int index = board.getNamesScore(input.next());
        if (index > 0) {
            System.out.println("Your highest score is at position: " + index);
        } else {
            System.out.println("No one has that name!");
        }
    }

    //REQUIRES: input be an integer
    //EFFECTS: gets the top score with the given name and displays its ranking
    public void frameShowTopScore() {
        String name = JOptionPane.showInputDialog("enter a name to find top score of");

        int index = board.getNamesScore(name);
        if (index > 0) {
            JOptionPane.showMessageDialog(this, "The highest score with that name is " + index);
        } else {
            JOptionPane.showMessageDialog(this, "No one has that name!");

        }
    }


    //Some of the code structure taken from the teller class
    // MODIFIES: this
    // EFFECTS: changes the difficulty
    public void changeDifficulty() {
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
    // EFFECTS: saves the leaderboard to a file

    public void saveGameApp() {
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
            System.out.println("Saved " + "Difficulty " + " to " + JSON_STORE_D);
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
            System.out.println("Loaded " + "Difficulty " + " from " + JSON_STORE_D);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_D);
        }
    }


    //Adapted from the Json
    // MODIFIES: this
    // EFFECTS: loads leaderboard from file
    public void loadGameApp() {
        try {
            board = jsonReader.read();
            System.out.println("Loaded " + "Leaderboard " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        displayLeaderboard();
    }


}
