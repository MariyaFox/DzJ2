package model;

import model.inter.Action;

public class Treadmill {
    private boolean doAction;
    private int height;

    public void runOnTreadmill(Action runable) {
        if (doAction) {
            runable.run();
        } else {
            System.out.println("Participant can not run on treadmill.");
        }
    }

    public Treadmill() {
        this.height = height;
    }

}
