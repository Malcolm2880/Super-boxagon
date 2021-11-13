package model;

//Player class that the user controls in the game.
public class Player {
    private int index = 0;

    //EFFECTS: Creates a player, with the default index of 0.

    public Player() {
        index = 0;
    }



    public int getIndex() {
        return index;
    }



    // REQUIRES: index to be greater than 0
    // MODIFIES: this
    // EFFECTS: sets the current player position.

    public void setIndex(int index) {
        this.index = --index;
    }

    // REQUIRES: diff + 1 is greater than current index
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


    // REQUIRES: index to be greater than 0 and less than the EnemyPattern's length
    // EFFECTS: checks if the current index means that the player has died
    public boolean isDead(EnemyPattern ep) {

        return ep.getPattern().substring(index,index + 1).equals("*");

    }



}
