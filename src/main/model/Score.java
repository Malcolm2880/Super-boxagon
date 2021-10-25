package model;

//Score class that stores the scores and names associated with them

import org.json.JSONObject;
import persistence.Writable;

public class Score implements Writable {
    private String name;
    private int score;

    //EFFECTS: Creates a Score object with the given name and score values
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("score", score);
        return json;
    }
}
