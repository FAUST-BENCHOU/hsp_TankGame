package tankgame03;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override

    public void run() {
        while (true) {
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

            setDirection((int)(Math.random()*4));

            if (isLive == false) {
                break;
            }
        }
    }
}
