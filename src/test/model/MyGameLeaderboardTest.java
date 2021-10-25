package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//Tests the leaderboard class
class MyGameLeaderboardTest {

    Leaderboard l ;
    Score s;
    @BeforeEach
    public void runBefore() {
       l = new Leaderboard();
       s = new Score("bob", 0);

    }


    @Test
    public void leaderBoardConstructorTest() {
        // Leaderboard l = new Leaderboard();
        //Score s = new Score("bob",0);
        l.addScore(s);
        assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());

        // System.out.println(l.getAllScores());

    }

    @Test
    public void leaderBoardGetSizeTest() {
        // Leaderboard l = new Leaderboard();
        //Score s = new Score("bob",0);
        assertEquals(0,l.getSize());

        l.addScore(s);
     //   assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());
        // System.out.println(l.getAllScores());
        assertEquals(1,l.getSize());
        l.addScore(s);
        l.addScore(s);
        l.addScore(s);
        l.addScore(s);
        assertEquals(5,l.getSize());


    }


    @Test
    public void leaderBoardAddTest() {
      //  Leaderboard l = new Leaderboard();
        //Score s = new Score("bob", 0);
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
        //Leaderboard l = new Leaderboard();
        assertEquals("The Leaderboard is as follows:", l.getAllScores());
        //Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());
        l.addScore(new Score("bobby", 10));
        l.addScore(new Score("obby",5));
        assertEquals("The Leaderboard is as follows:" + "\n" + "bobby 10" + "\n" + "obby 5" + "\n" + "bob 0",l.getAllScores());
    }
    @Test
    public void leaderBoardGetTopTest() {
      //  Leaderboard l = new Leaderboard();
        //Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals("bob 0", l.getTopScore());


        l.addScore(new Score("bobby", 10));

        assertEquals("bobby 10", l.getTopScore());
        l.addScore(new Score("obby", 5));

        assertEquals("bobby 10", l.getTopScore());

    }

    @Test
    public void leaderBoardGetNamesScoreTest() {
      //  Leaderboard l = new Leaderboard();
        //Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals(1, l.getNamesScore("bob"));
        l.addScore(new Score("bob", -5));
        assertEquals(1, l.getNamesScore("bob"));
        l.addScore(new Score("bob", 10));
        l.addScore(new Score("champ", 100));
        assertEquals(2, l.getNamesScore("bob"));
        assertEquals(-1, l.getNamesScore("chara"));


    }
}