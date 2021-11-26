package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

//Class that stores Scores, and allows retrieval of Scores.
//Note that scores are automatically inputted in a sorted order.

public class Leaderboard implements Writable {


    ArrayList<Score> names;


    public ArrayList<Score> getNames() {
        return names;
    }

    // EFFECTS: Creates an empty leaderboard.
    public Leaderboard() {
        names = new ArrayList<Score>();
        EventLog.getInstance().logEvent(new Event("Wiped the board"));

    }


    public int getSize() {
        return names.size();
    }

    // REQUIRES: Score be not null, and have a proper name and value
    // MODIFIES: this
    // EFFECTS: add the given score. returns if successful. Higher scores will be listed higher

    public void addScore(Score s) {
        if (names.size() == 0) {
            EventLog.getInstance().logEvent(new Event("Added Score: " + s.getName() + " " + s.getScore()));

            names.add(s);
            return;

        }
        for (int i = 0; i < names.size(); i++) {
            if (s.getScore() >= names.get(i).getScore()) {
                names.add(i, s);
                EventLog.getInstance().logEvent(new Event("Added Score: " + s.getName() + " " + s.getScore()));

                return;
            }

        }
        EventLog.getInstance().logEvent(new Event("Added Score: " + s.getName() + " " + s.getScore()));

        names.add(s);
    }


    // EFFECTS: returns the leaderboard

    public String getAllScores() {
        String bob = "The Leaderboard is as follows:";

        for (Score i : names) {
            bob += "\n";

            bob += i.getName() + " ";
            bob += i.getScore();
        }
        return bob;
    }


    // REQUIRES: Leaderboard is not empty
    // EFFECTS: returns the top score.

    public String getTopScore() {
        String bob = "";
        bob += names.get(0).getName() + " ";
        bob += names.get(0).getScore();
        return bob;
    }

    // EFFECTS: returns the top score with the given name.
    public int getNamesScore(String givenName) {
        int index = 1;
        for (Score i : names) {
            if (i.getName().equalsIgnoreCase(givenName)) {

                return index;
            }
            index++;
        }

        return -1;
    }


    //Note this method is almost completely ripped from
//Github:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//EFFECTS: returns the conversion of the current object to a persistent object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Scores", scoresToJson());
        return json;
    }

    //Note this method is almost completely ripped from
    //Github:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: returns Scores in this leaderboard as a JSON array
    public JSONArray scoresToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Score s : names) {
            EventLog.getInstance().logEvent(new Event("Saved Score: " + s.getName() + " " + s.getScore()));

            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
