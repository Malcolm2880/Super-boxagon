
package persistence;


import model.Leaderboard;
import model.Score;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Leaderboard lb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBoard() {
        JsonReader reader = new JsonReader("./data/Empty.json");
        try {
            Leaderboard lb = reader.read();
            assertEquals(0, lb.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBoard() {
        JsonReader reader = new JsonReader("./data/General.json");
        try {
            Leaderboard lb = reader.read();
          //  List<Score> Scores = lb.getNames();
            assertEquals(3, lb.getSize());
            assertEquals("The Leaderboard is as follows:" + "\n" + "Chara 13" + "\n" + "Kris 5" + "\n" + "Frisk 2",lb.getAllScores());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderDifficulty() {
        JsonReader reader = new JsonReader("./data/MyDifficultyTest.json");
        try {
            int d = reader.readInt();
            assertEquals(1, d);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

