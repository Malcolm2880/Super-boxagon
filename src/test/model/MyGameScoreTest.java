package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    }