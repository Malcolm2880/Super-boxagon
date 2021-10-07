package model;
//stores scores

public class Score {
    private String name;
    private int score;

    public Score(String n, int s) {
        name = n;
        score = s;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



}
