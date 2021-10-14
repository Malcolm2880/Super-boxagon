package model;



//This class generates and stores a pattern for the player to dodge.

public class EnemyPattern {
    private String pattern = "";

    // REQUIRES: difficulty to be a positive integer
    // EFFECTS: Creates an Enemy Pattern, and generates a random pattern based on difficulty.
    public EnemyPattern(int difficulty) {
        pattern = generatePattern((int) (Math.random() * (difficulty + 1)), difficulty + 1);

    }

    public String getPattern() {

        return this.pattern;
    }



    // REQUIRES: value to be strictly smaller than difficulty
    // EFFECTS: generates the current pattern
    public String generatePattern(int value, int difficulty) {
        String enemy = "";
        for (int i = 0; i < difficulty; i++) {
            if (i == value) {
                enemy += "@";
            } else {
                enemy += "*";
            }
        }
        return enemy;

    }


}
