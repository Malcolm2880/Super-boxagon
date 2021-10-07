package model;

public class Player {
    private int index = 0;

    public Player() {
        index = 3;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String generatePosition() {
        String bob = "";
        for (int i = 0; i < 6; i++) {
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
