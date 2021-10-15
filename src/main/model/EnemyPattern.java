package model;

public class EnemyPattern {
    private String pattern = "";

// REQUIRES: d to be a positive integer
    public EnemyPattern(int difficulty) {
        pattern = generatePattern((int) (Math.random() * (difficulty + 1)), difficulty + 1);

    }

    // EFFECTS: gets the current pattern
    public String getPattern() {

        return this.pattern;
    }



    // REQUIRES: value to be smaller than d
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
