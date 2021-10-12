package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyGameLeaderboardTest {

    @Test
    public void leaderBoardConstructorTest() {
        Leaderboard l = new Leaderboard();
        Score s = new Score("bob",0);
        l.addScore(s);
        assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());

        // System.out.println(l.getAllScores());

    }


    @Test
    public void leaderBoardAddTest() {
        Leaderboard l = new Leaderboard();
        Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());
       l.addScore(new Score("bobby", 10));
        l.addScore(new Score("obby",5));
        assertEquals("bobby 10",l.getTopScore());
        assertEquals("The Leaderboard is as follows:" + "\n" + "bobby 10" + "\n" + "obby 5" + "\n" + "bob 0",l.getAllScores());
        l.addScore(new Score("fool",-5));
        assertEquals("The Leaderboard is as follows:" + "\n" + "bobby 10" + "\n" + "obby 5" + "\n" + "bob 0" + "\n" + "fool -5",l.getAllScores());

    }
    @Test
    public void leaderBoardGetAllTest() {
        Leaderboard l = new Leaderboard();
        assertEquals("The Leaderboard is as follows:", l.getAllScores());
        Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());
        l.addScore(new Score("bobby", 10));
        l.addScore(new Score("obby",5));
        assertEquals("The Leaderboard is as follows:" + "\n" + "bobby 10" + "\n" + "obby 5" + "\n" + "bob 0",l.getAllScores());
    }
    @Test
    public void leaderBoardGetTopTest() {
        Leaderboard l = new Leaderboard();
        Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals("bob 0", l.getTopScore());


        l.addScore(new Score("bobby", 10));

        assertEquals("bobby 10", l.getTopScore());
        l.addScore(new Score("obby", 5));

        assertEquals("bobby 10", l.getTopScore());

    }
}