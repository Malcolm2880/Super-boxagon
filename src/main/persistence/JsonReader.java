package persistence;

import model.Event;
import model.EventLog;
import model.Leaderboard;
import model.Score;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Note this class is almost completely ripped from
//Github:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git




// Represents a reader that reads leaderboard from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads leaderboard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Leaderboard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeaderBoard(jsonObject);
    }

    public int readInt() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getInt("Difficulty");
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses leaderboard from JSON object and returns it
    private Leaderboard parseLeaderBoard(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        Leaderboard lb = new Leaderboard();
        addScores(lb, jsonObject);
        return lb;
    }

    // MODIFIES: lb
    // EFFECTS: parses Scores from JSON object and adds them to leaderboard
    private void addScores(Leaderboard lb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Scores");
        EventLog.getInstance().logEvent(new Event("Loaded Scores"));

        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(lb, nextScore);
        }
    }

    // MODIFIES: lb
    // EFFECTS: parses Score from JSON object and adds it to leaderboard
    private void addScore(Leaderboard lb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int score = jsonObject.getInt("score");
        Score s = new Score(name, score);
        lb.addScore(s);
    }
}