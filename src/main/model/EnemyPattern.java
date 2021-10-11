package model;

public class EnemyPattern {
    private String pattern = "";


    public EnemyPattern(int d) {
        pattern = generatePattern((int) (Math.random() * d + 1), d + 1);

    }

    // EFFECTS: gets the current pattern
    //todo delete?
    public String getPattern() {
        return this.pattern;
    }


    // EFFECTS: generates the current pattern
    private String generatePattern(int value, int d) {
        System.out.println("The current D is");
        System.out.println(d);
        System.out.println("The current value is");
        System.out.println(value);


        String enemy = "";
        for (int i = 0; i < d; i++) {
            if (i == value) {
                enemy += "@";
            } else {
                enemy += "*";
            }
        }
        return enemy;

    }


}
