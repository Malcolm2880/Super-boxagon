package model;

public class EnemyPattern {
    private String pattern = "";

    public String getPattern() {
        return this.pattern;
    }



    public EnemyPattern() {
        pattern = generatePattern((int) (Math.random() * 100000));
    }

    private String generatePattern(int value) {
        String enemy = "";
        String bob = Integer.toString(value);
        for (int i = 0; i < bob.length(); i++) {
            String temp = bob.substring(i, i + 1);
            int num = Integer.parseInt(temp);
            if (num % 3 == 0) {
                enemy += "@";
            } else if (num % 2 == 0) {
                enemy += "*";

            } else {
                enemy += "$";
            }

        }
        return enemy;

    }




}
