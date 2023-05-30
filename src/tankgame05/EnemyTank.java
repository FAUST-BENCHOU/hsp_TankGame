package tankgame05;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();

    public Vector<Shot> getShots() {
        return shots;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    Vector<EnemyTank> enemyTanks = new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public boolean isTouchEnemyTank() {
        switch (this.getDirection()) {
            case 0: //上
                //让当前的this 敌人坦克 和 其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一辆敌人的坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (this != enemyTank) {
                        /*
                        1.如果敌人坦克是上/下方向   x的范围是什么【enemyTank.getX() ,enemyTank.getX() + 40】
                                                y的范围是什么【enemyTank.getY() ,enemyTank.getY() + 60】
                         */
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            //2.当前坦克的左上角坐标【this.getX(),this.getY()】
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克的右上角坐标【this.getX() + 40,this.getY()】
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                        /*
                        1.如果敌人坦克是左/右方向   x的范围是什么【enemyTank.getX() ,enemyTank.getX() + 60】
                                                y的范围是什么【enemyTank.getY() ,enemyTank.getY() + 40】
                         */
                        //如果敌人坦克是左/右方向
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            //2.当前坦克的左上角坐标【this.getX(),this.getY()】
                            if (this.getX() >= enemyTank.getX()-10 && this.getX() <= enemyTank.getX() + 60-10 &&
                                    this.getY() >= enemyTank.getY()+10 && this.getY() <= enemyTank.getY() + 40+10) {
                                return true;
                            }
                            //3.当前坦克的右上角坐标【this.getX() + 40,this.getY()】
                            if (this.getX() + 40 >= enemyTank.getX()-10 && this.getX() + 40 <= enemyTank.getX() + 60 -10&&
                                    this.getY() >= enemyTank.getY()+10 && this.getY() <= enemyTank.getY() + 40+10) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: //右
                //让当前的this 敌人坦克 和 其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一辆敌人的坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (this != enemyTank) {
                        /*
                        1.如果敌人坦克是上/下方向   x的范围是什么【enemyTank.getX() ,enemyTank.getX() + 40】
                                                y的范围是什么【enemyTank.getY(),enemyTank.getY()  + 40】
                         */
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            //2.当前坦克的右上角坐标【this.getX() + 60,this.getY()】
                            if (this.getX() + 60-10 >= enemyTank.getX() && this.getX() + 60 -10<= enemyTank.getX() + 40 &&
                                    this.getY()+10 >= enemyTank.getY() && this.getY()+10 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克的右下角坐标【this.getX() + 60,this.getY() + 40】
                            if (this.getX() + 60 -10>= enemyTank.getX() && this.getX() + 60 -10<= enemyTank.getX() + 40 &&
                                    this.getY() + 40 +10>= enemyTank.getY() && this.getY() + 40 +10<= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        /*
                        1.如果敌人坦克是左/右方向   x的范围是什么【enemyTank.getX(),enemyTank.getX() + 60】
                                                y的范围是什么【enemyTank.getY(),enemyTank.getX() + 40】
                         */
                        //如果敌人坦克是左/右方向
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            //2.当前坦克的右上角坐标【this.getX() + 60,this.getY()】
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3.当前坦克的右下角坐标【this.getX() + 60,this.getY()  + 40】
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60 &&
                                    this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //下
                //让当前的this 敌人坦克 和 其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一辆敌人的坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (this != enemyTank) {
                        /*
                        1.如果敌人坦克是上/下方向   x的范围是什么【enemyTank.getX() ,enemyTank.getX() + 40】
                                                y的范围是什么【enemyTank.getY(),enemyTank.getY()  + 60】
                         */
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            //2.当前坦克的左下角坐标【this.getX(),this.getY() + 60】
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克的右下角坐标【this.getX() + 40,this.getY() + 60】
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40 &&
                                    this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        /*
                        1.如果敌人坦克是左/右方向   x的范围是什么【enemyTank.getX(),enemyTank.getX() + 60】
                                                y的范围是什么【enemyTank.getY(),enemyTank.getX() + 40】
                         */
                        //如果敌人坦克是左/右方向
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            //2.当前坦克的左下角坐标【this.getX(),this.getY() + 60】
                            if (this.getX() >= enemyTank.getX()-10 && this.getX() <= enemyTank.getX() + 60 -10&&
                                    this.getY() + 60 >= enemyTank.getY() +10&& this.getY() + 60 <= enemyTank.getY() + 40+10) {
                                return true;
                            }
                            //3.当前坦克的右下角坐标【this.getX() + 40,this.getY() + 60】
                            if (this.getX() + 40 >= enemyTank.getX() -10&& this.getX() + 40 <= enemyTank.getX() + 60-10 &&
                                    this.getY() + 60 >= enemyTank.getY() +10&& this.getY() + 60 <= enemyTank.getY() + 40+10) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //左
                //让当前的this 敌人坦克 和 其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一辆敌人的坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (this != enemyTank) {
                        /*
                        1.如果敌人坦克是上/下方向   x的范围是什么【enemyTank.getX() ,enemyTank.getX() + 40】
                                                y的范围是什么【enemyTank.getY(),enemyTank.getY()  + 60】
                         */
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            //2.当前坦克的左上角坐标【this.getX(),this.getY()】
                            if (this.getX()-10 >= enemyTank.getX() && this.getX()-10 <= enemyTank.getX() + 40 &&
                                    this.getY() +10>= enemyTank.getY() && this.getY()+10 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3.当前坦克的左下角坐标【this.getX(),this.getY() + 40】
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        /*
                        1.如果敌人坦克是左/右方向   x的范围是什么【enemyTank.getX(),enemyTank.getX() + 60】
                                                y的范围是什么【enemyTank.getY(),enemyTank.getX() + 40】
                         */
                        //如果敌人坦克是左/右方向
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            //2.当前坦克的左上角坐标【this.getX(),this.getY()】
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3.当前坦克的左下角坐标【this.getX(),this.getY() + 40】
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
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
                        }if (!isTouchEnemyTank()&&getY()>=0) {
                        moveUp();
                        }
                        break;

                    case 1:

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!isTouchEnemyTank()&&(getX()-10)>=0) {
                            moveRight();
                        }
                        break;
                    case 2:

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!isTouchEnemyTank()&&getY()>=0) {
                            moveDown();
                        }
                        break;
                    case 3:

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (!isTouchEnemyTank()&&(getX()-10)>=0) {
                            moveLeft();
                        }
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
