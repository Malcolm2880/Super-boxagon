package model;

import java.util.*;

public class Leaderboard {
    ArrayList<Score> names;

    public Leaderboard() {
        names = new ArrayList<Score>();
    }


    public boolean addScore(Score s) {
        if (names.size() == 0) {
            names.add(s);
            return true;
        }
        for (int i = 0; i < names.size();i++) {
            if (s.getScore() >= names.get(i).getScore()) {
                names.add(i,s);
                return true;
            }
        }
        return false;
    }

    public String getAllScores() {
        String bob = "";
        for (Score i : names) {
            if (bob.length() != 0) {
                bob += "\n";
            }
            bob += i.getName() + " ";
            bob += i.getScore();
        }
        return bob;
    }

    public String getTopScore() {
        String bob = "";
        bob += names.get(0).getName() + " ";
        bob += names.get(0).getScore();
        return bob;
    }
}
