package model;

import model.inter.Action;

public class Wall {
    private boolean doAction;
    private int length;

    public void jumpOverWall(Action jumpable) {
        if (doAction) {
            jumpable.jump();
        } else {
            System.out.println("Participant can not jump over wall.");
        }
    }
    public Wall() {
        this.length = length;
    }
}
