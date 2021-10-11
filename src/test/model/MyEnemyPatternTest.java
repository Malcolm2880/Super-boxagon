package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MyEnemyPatternTest {

    @Test
    public void enemyPatternConstructorTest() {
        EnemyPattern ep = new EnemyPattern(5);
        assertFalse(ep.getPattern().equals(""));
        EnemyPattern ep2 = new EnemyPattern(0);
        assertFalse(ep.getPattern().equals(""));

        // System.out.println(l.getAllScores());

    }

    @Test
    public void enemyPatternGetPatternTest() {
        EnemyPattern ep = new EnemyPattern(5);
        assertFalse(ep.getPattern().equals(""));
        assertTrue(ep.getPattern().contains("@"));
    }


    @Test
    public void enemyPatternGenerateSmallDifficultyTest() {
        EnemyPattern ep = new EnemyPattern(1);
       assertTrue(ep.generatePattern(0,1).equals("@"));
        assertTrue(ep.generatePattern(1,2).equals("*@"));
        assertTrue(ep.generatePattern(0,2).equals("@*"));
        assertTrue(ep.generatePattern(0,5).equals("@****"));
        assertTrue(ep.generatePattern(3,5).equals("***@*"));
        assertTrue(ep.generatePattern(4,5).equals("****@"));




    }
    @Test
    public void enemyPatternGenerateLargeDifficultyTest() {
        EnemyPattern ep = new EnemyPattern(1);
        assertTrue(ep.generatePattern(0, 5).equals("@****"));
        assertTrue(ep.generatePattern(3, 5).equals("***@*"));
        assertTrue(ep.generatePattern(4, 5).equals("****@"));
    }
}