package tankgame02;

public class Hero extends Tank {

    public Hero(int x, int y) {
        super(x, y);
    }

    Shot shot = null;

    public void shotEnemyTank(){
        switch (getDirection()) {
            case 0: //向上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1: //向右
                shot = new Shot(getX() + 60-10, getY() + 20+10, 1);
                break;
            case 2: //向下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3: //向左
                shot = new Shot(getX()-10, getY() + 20+10, 3);
                break;
        }
        new Thread(shot).start();

    }
}
