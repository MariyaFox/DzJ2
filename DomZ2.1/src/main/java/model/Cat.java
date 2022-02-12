package model;

import model.inter.Jumpable;
import model.inter.Runable;

public class Cat implements Runable, Jumpable {

    protected int runLength;
    protected int jumpHeight;


    @Override
    public void jump() {

        System.out.println("Кот прыгает");
    }

    @Override
    public void run() {

        System.out.println("Кот бегает");
    }

    public void run(int length) {
        if ((length >= 0) && (length <= runLength)) System.out.println("run: true");
        else {
            System.out.println("run: false");
        }
    }

    public void jupm(int height) {
        if ((height >= 0) && (height <= jumpHeight)) System.out.println("jump: true");
        else {
            System.out.println("jump: false");
        }
    }

}

