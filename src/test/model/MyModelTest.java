package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyGameTest {

    @Test
    public void leaderTest() {
        Leaderboard l = new Leaderboard();

        Score s = new Score("bob",0);
        l.addScore(s);
       // System.out.println(l.getAllScores());

        l.addScore(new Score("bobby", 20 ));
        l.addScore(new Score("obby",5));
        assertEquals("bobby 20",l.getTopScore());
        l.addScore(new Score("champ", 100));
        assertEquals("champ 100",l.getTopScore());
        l.addScore(new Score("failure", 0));
        assertEquals("champ 100",l.getTopScore());
        assertEquals(" ", l.getAllScores());




    }

}