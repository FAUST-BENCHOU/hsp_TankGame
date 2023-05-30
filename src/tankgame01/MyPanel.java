package tankgame01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//MyPanel类
public class MyPanel extends JPanel implements KeyListener {
    //定义我的坦克
    Hero hero = null;
    //定义敌人坦克，放入到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    public MyPanel() {
        hero = new Hero(100, 100);//初始化自己坦克
        //初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
            //创建一个敌人的坦克
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            //设置方向
            enemyTank.setDirection(2);
            //加入
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色

        //画出自己坦克-封装方法
        drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);

        //画出敌人的坦克, 遍历Vector
        for (EnemyTank enemyTank :enemyTanks) {
            //取出坦克
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
        }


    }

    //编写方法，画出坦克

    /**
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {

        //根据不同类型坦克，设置不同颜色
        switch (type) {
            case 0: //敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //我的坦克
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克方向，来绘制对应形状坦克
        //direct 表示方向(0: 向上 1 向右 2 向下 3 向左 )
        //
        switch (direct) {
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x-10, y+10, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x-10, y + 30+10, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10-10, y + 10+10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20-10, y + 10+10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30-10, y + 20+10, x + 60-10, y + 20+10);//画出炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x-10, y+10, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x-10, y + 30+10, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10-10, y + 10+10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20-10, y + 10+10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30-10, y + 20+10, x-10, y + 20+10);//画出炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下W键
            //改变坦克的方向
            hero.setDirection(0);//
            //修改坦克的坐标 y -= 1
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//D键, 向右
            hero.setDirection(1);
            hero.moveRight();

        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S键
            hero.setDirection(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A键
            hero.setDirection(3);
            hero.moveLeft();
        }
        //让面板重绘
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}