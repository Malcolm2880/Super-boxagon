



package persistence;


import model.Leaderboard;

import model.Score;

import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest  {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Leaderboard lb = new Leaderboard();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLeaderboard() {
        try {
            Leaderboard lb = new Leaderboard();
            JsonWriter writer = new JsonWriter("./data/Empty.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/Empty.json");
            lb = reader.read();
            assertEquals(0, lb.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLeaderboard() {
        try {
            Leaderboard lb = new Leaderboard();
            lb.addScore(new Score( "Frisk" , 2));
            lb.addScore(new Score( "Chara" , 13));
            lb.addScore(new Score( "Kris" , 5));

            JsonWriter writer = new JsonWriter("./data/General.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/General.json");
            lb = reader.read();

            assertEquals("The Leaderboard is as follows:" + "\n" + "Chara 13" + "\n" + "Kris 5" + "\n" + "Frisk 2",lb.getAllScores());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

