package newTankGame;

public class Tank {
    private int x;
    private int y;
    private int direction;
    private int speed=1;
    boolean isLive = true;
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveUp(){
        y=y-2;
    }
    public void moveDown(){
        y=y+2;
    }
    public void moveLeft(){
        x=x-2;
    }
    public void moveRight(){
        x=x+2;
    }
}
