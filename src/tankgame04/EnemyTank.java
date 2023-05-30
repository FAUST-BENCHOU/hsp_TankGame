package tankgame04;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();


    public EnemyTank(int x, int y) {
        super(x, y);
    }


    @Override

    public void run() {
        while (true) {

            if (isLive == true && shots.size() < 10) {
                Shot s = null;
                switch (getDirection()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }

            for (int i = 0; i < 30; i++) {
                switch (getDirection()) {
                    case 0:

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        moveUp();

                        break;

                    case 1:

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                            moveRight();

                        break;
                    case 2:

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                            moveDown();

                        break;
                    case 3:

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                            moveLeft();

                        break;
                }
            }

            setDirection((int) (Math.random() * 4));

            if (isLive == false) {
                break;
            }
        }
    }
}
