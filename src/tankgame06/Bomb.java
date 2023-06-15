package tankgame06;

public class Bomb {
    int x, y;
    int life = 9;
    boolean isLive = true;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLife() {
        return life;
    }

    public boolean isLive() {
        return isLive;
    }

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void lifeDown() {
        if (life > 0) {
            life--;
        } else {
            isLive = false;
        }
    }
}
