import model.*;
import model.inter.Action;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Human human = new Human();
        Robot robot = new Robot();

        cat.run();
        cat.jump();
        human.run();
        human.jump();
        robot.run();
        robot.jump();

        Action [] participant = new Action[3];
        Team team = new Team("My Team", participant);

        Object [] barrier = new Object[2];
        barrier[0] = new Wall();
        barrier[1] = new Treadmill();

        for (Object o : barrier) {
            for (Action action : participant) {
                if (o instanceof Wall) {
                    ((Wall) o).jumpOverWall(action);
                }
                if (o instanceof Treadmill) {
                    ((Treadmill) o).runOnTreadmill(action);
                }
            }

        }
    }
}


