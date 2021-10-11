package model;

public class Player {
    private int index = 0;

    public Player() {
        index = 3;
    }

// EFFECTS: gets the current index
    public int getIndex() {
        return index;
    }

    // MODIFIES: this
    // EFFECTS: sets the current player position.

    public void setIndex(int index) {
        this.index = --index;
    }

    // EFFECTS: displays the position of the player
    public String generatePosition(int diff) {
        String bob = "";
        for (int i = 0; i < diff + 1; i++) {
            if (i == index) {
                bob += "^";
            } else {
                bob += "-";
            }
        }
        return bob;
    }

    public boolean isDead(EnemyPattern ep) {
        return ep.getPattern().substring(index,index + 1).equals("*");
    }



}
