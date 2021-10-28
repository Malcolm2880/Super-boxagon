package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//Tests the Score Class
class MyGameScoreTest {


    Score s;
    @BeforeEach
    public void runBefore() {
        s = new Score("bob", 0);

    }

    @Test
    public void scoreConstructorTest() {
      //  Score s = new Score("bob",0);
      assertEquals(s.getScore(),0);
      assertTrue(s.getName().equals("bob"));


    }
    @Test
    public void scoreGetNameTest() {
      //  Score s = new Score("bob",0);
        assertTrue(s.getName().equals("bob"));
        Score s2 = new Score("bobby",0);
        assertTrue(s2.getName().equals("bobby"));
        Score s3 = new Score("kris",99);
        assertTrue(s3.getName().equals("kris"));




    }

    @Test
    public void scoreGetScoreTest() {
       // Score s = new Score("bob",0);
        assertTrue(s.getScore() == 0);
        Score s2 = new Score("bobby",0);
        assertTrue(s2.getScore() == 0);
        Score s3 = new Score("kris",99);
        assertTrue(s3.getScore() == 99);




    }
    @Test
    void generalScoreToJsonTest() {
        try {
            Leaderboard lb = new Leaderboard();
            lb.addScore(new Score( "Frisk" , 2));
            lb.addScore(new Score( "Chara" , 13));
            lb.addScore(new Score( "Kris" , 5));
            Score sc = new Score("Frisk",2);
            JsonWriter writer = new JsonWriter("./data/General.json");
            writer.open();
            writer.write(lb);
            writer.close();


            JsonReader reader = new JsonReader("./data/General.json");
            assertTrue(reader.read().toJson().toString().contains(sc.toJson().toString()));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    }