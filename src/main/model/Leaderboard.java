package model;

import java.util.*;

public class Leaderboard {
    ArrayList<Score> names;

    public Leaderboard() {
        names = new ArrayList<Score>();
    }

    // REQUIRES: Score be not null, and have a proper name and value
    // MODIFIES: this
    // EFFECTS: add the given score. returns if successful. Higher scores will be listed higher

    public void addScore(Score s) {
        if (names.size() == 0) {
            names.add(s);
            return;

        }
        for (int i = 0; i < names.size();i++) {
            if (s.getScore() >= names.get(i).getScore()) {
                names.add(i,s);
                return;
            }

        }
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
}
