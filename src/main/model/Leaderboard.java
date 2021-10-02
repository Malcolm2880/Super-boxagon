package model;

import java.util.*;

public class Leaderboard {
    PriorityQueue<Score> names;

    public Leaderboard() {
        names = new PriorityQueue<Score>();
    }


    private void addScore(Score s) {
        names.add(s);
    }

    public String getAllScores() {
        String bob = "";
        for (Score i : names) {
            bob += i.getName();
            bob += i.getScore() + '\n';
        }
        return bob;
    }

    private String getTopScore() {

        return names.peek().getName();
    }
}
