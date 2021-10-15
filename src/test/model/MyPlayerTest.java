package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests the player class
class MyPlayerTest {

    Player p;
    @BeforeEach
    public void runBefore() {
        p = new Player();


    }


    @Test
    public void playerConstructorTest() {
       // Player p = new Player();
        assertTrue(p.getIndex() == 3);

        // System.out.println(l.getAllScores());

    }

    @Test
    public void playerGetterTest() {
       // Player p = new Player();
        assertTrue(p.getIndex() == 3);
        p.setIndex(5);
        assertTrue(p.getIndex() == 4);
        p.setIndex(1);
        assertTrue(p.getIndex() == 0);
        // System.out.println(l.getAllScores());

    }
    @Test
    public void playerSetterTest() {
       // Player p = new Player();
        p.setIndex(4);
        assertTrue(p.getIndex() == 3);
        p.setIndex(1);
        assertTrue(p.getIndex() == 0);
      p.setIndex(10);
        assertTrue(p.getIndex() == 9);
        // System.out.println(l.getAllScores());

    }

@Test
    public void playerGeneratePosition() {
       // Player p = new Player();

    p.setIndex(1);
    assertTrue(p.generatePosition(1).equals("^-"));
    assertTrue(p.generatePosition(0).equals("^"));
    p.setIndex(4);
    assertTrue(p.generatePosition(3).equals("---^"));
    p.setIndex(10);
    assertTrue(p.generatePosition(10).equals("---------^-"));

        // System.out.println(l.getAllScores());

    }

    @Test
    public void playerIsDeadTest() {
       // Player p = new Player();
        EnemyPattern ep = new EnemyPattern(1);
        if(ep.getPattern().equals("@*")) {
            p.setIndex(1);
            assertFalse(p.isDead(ep));
            p.setIndex(2);
            assertTrue(p.isDead(ep));
        } else {
            p.setIndex(1);
            assertTrue(p.isDead(ep));
            p.setIndex(2);
            assertFalse(p.isDead(ep));

        }




    }

}