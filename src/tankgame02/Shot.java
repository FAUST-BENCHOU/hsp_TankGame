package tankgame02;

public class Shot implements Runnable{
    int x;
    int y;
    int direct = 0; //子弹方向
    int speed = 2; //子弹的速度
    boolean isLive = true; //子弹是否还存活


    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0:
                    y=y-speed;
                    break;
                case 1:
                    x=x+speed;
                    break;
                case 2:
                    y=y+speed;
                    break;
                case 3:
                    x=x-speed;
                    break;

            }
            System.out.println("子弹 x=" + x + " y=" + y);
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750&&isLive)) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }

        }
    }
}
