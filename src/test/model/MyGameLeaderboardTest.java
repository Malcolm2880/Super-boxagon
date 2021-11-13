package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Tests the leaderboard class
class MyGameLeaderboardTest {

    Leaderboard l;
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
        assertEquals(0, l.getSize());

        l.addScore(s);
        //   assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());
        // System.out.println(l.getAllScores());
        assertEquals(1, l.getSize());
        l.addScore(s);
        l.addScore(s);
        l.addScore(s);
        l.addScore(s);
        assertEquals(5, l.getSize());


    }


    @Test
    public void leaderBoardAddTest() {
        //  Leaderboard l = new Leaderboard();
        //Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());
        l.addScore(new Score("bobby", 10));
        l.addScore(new Score("obby", 5));
        assertEquals("bobby 10", l.getTopScore());
        assertEquals("The Leaderboard is as follows:" + "\n" + "bobby 10" + "\n" + "obby 5" + "\n" + "bob 0", l.getAllScores());
        l.addScore(new Score("fool", -5));
        assertEquals("The Leaderboard is as follows:" + "\n" + "bobby 10" + "\n" + "obby 5" + "\n" + "bob 0" + "\n" + "fool -5", l.getAllScores());

    }

    @Test
    public void leaderBoardGetNamesTest() {
        //  Leaderboard l = new Leaderboard();
        //Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());
        l.addScore(new Score("bobby", 10));
        l.addScore(new Score("obby", 5));
        ArrayList<Score> names = l.getNames();
        assertFalse(names.isEmpty());
        for (Score s : names) {
            System.out.println(s.getName());
        }
        assertEquals(l.getNamesScore(names.get(0).getName()), 1);
    }


    @Test
    public void leaderBoardGetAllTest() {
        //Leaderboard l = new Leaderboard();
        assertEquals("The Leaderboard is as follows:", l.getAllScores());
        //Score s = new Score("bob", 0);
        l.addScore(s);
        assertEquals("The Leaderboard is as follows:" + "\n" + "bob 0", l.getAllScores());
        l.addScore(new Score("bobby", 10));
        l.addScore(new Score("obby", 5));
        assertEquals("The Leaderboard is as follows:" + "\n" + "bobby 10" + "\n" + "obby 5" + "\n" + "bob 0", l.getAllScores());
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

    @Test
    void generalLeaderboardToJsonTest() {
        try {
            Leaderboard lb = new Leaderboard();
            JsonWriter writer = new JsonWriter("./data/Empty.json");
            writer.open();
            writer.write(lb);
            writer.close();
            Leaderboard lb2 = new Leaderboard();

            JsonReader reader = new JsonReader("./data/Empty.json");
            assertTrue(lb2.toJson().toString().equals(reader.read().toJson().toString()));
            lb = reader.read();
            assertEquals(0, lb.getSize());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void generalLeaderboardScoresToJsonTest() {
        try {
            Leaderboard lb = new Leaderboard();
            lb.addScore(new Score("Frisk", 2));
            lb.addScore(new Score("Chara", 13));
            lb.addScore(new Score("Kris", 5));

            JsonWriter writer = new JsonWriter("./data/General.json");
            writer.open();
            writer.write(lb);
            writer.close();
            Leaderboard lb2 = new Leaderboard();
            lb2.addScore(new Score("Frisk", 2));
            lb2.addScore(new Score("Chara", 13));
            lb2.addScore(new Score("Kris", 5));

            JsonReader reader = new JsonReader("./data/General.json");
            assertTrue(lb2.toJson().getJSONArray("Scores").toString().equals(reader.read().toJson().getJSONArray("Scores").toString()));
            lb = reader.read();

            assertEquals("The Leaderboard is as follows:" + "\n" + "Chara 13" + "\n" + "Kris 5" + "\n" + "Frisk 2", lb.getAllScores());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}