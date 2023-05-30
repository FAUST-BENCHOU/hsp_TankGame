package tankgame05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

//MyPanel类
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    //定义敌人坦克，放入到Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    Vector<Node> nodes = new Vector<>();

    //定义三张炸弹图片，用于显示爆炸效果
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;
    int enemyTankSize = 5;

    public MyPanel(String t) throws IOException {
        hero = new Hero(100, 200);//初始化自己坦克
        nodes = Recorder.readFile();
        //获取项目路径
        //2.初始化图片
        img1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        img3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
        System.out.println(t);
        if (t.equals("1")) {
            Recorder.setAllEnemyTankNum(0);
            for (int i = 0; i < enemyTankSize; i++) {
                EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                enemyTank.setDirection(2);
                //启动敌人坦克线程
                new Thread(enemyTank).start();
                //给该enemyTank 加入一颗子弹
                Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                //加入到
                enemyTank.getShots().add(shot);
                //为每个坦克，放入所有的敌人对象
                enemyTank.setEnemyTanks(enemyTanks);
                Recorder.setEnemyTanks(enemyTanks);
                //启动shot
                new Thread(shot).start();
                enemyTanks.add(enemyTank);
            }
        } else if (t.equals("0")) {
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                enemyTank.setDirection(node.getDirect());
                //启动敌人坦克线程
                new Thread(enemyTank).start();
                //给该enemyTank 加入一颗子弹
                Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                //加入到
                enemyTank.getShots().add(shot);
                //为每个坦克，放入所有的敌人对象
                enemyTank.setEnemyTanks(enemyTanks);
                Recorder.setEnemyTanks(enemyTanks);
                //启动shot
                new Thread(shot).start();
                enemyTanks.add(enemyTank);

            }
        }
    }


    public void showInfo(Graphics g) {

        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累积击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);//画出一个敌方坦克
        g.setColor(Color.BLACK);//这里需要重新设置成黑色
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色

        //画出自己坦克-封装方法
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);
        }
        Iterator<Shot> iterator = hero.shots.iterator();
        while (iterator.hasNext()) {
            Shot shot = iterator.next();
            if (shot != null && shot.isLive) {
                System.out.println("子弹被绘制...");
                g.draw3DRect(shot.x, shot.y, 3, 3, false);
            } else {
                iterator.remove();
            }
        }


        //画出敌人的坦克, 遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //取出坦克
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
                //绘制敌人坦克子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    //子弹存活的时候才绘制
                    if (shot.isLive()) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        //子弹出边界了，移除子弹
                        enemyTank.shots.remove(shot);
                    }
                }
            } else {
                enemyTanks.remove(enemyTank);
            }
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            //根据当前炸弹的life值画出对应的图片
            if (bomb.getLife() > 6) {
                g.drawImage(img1, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.getLife() > 3) {
                g.drawImage(img2, bomb.getX(), bomb.getY(), 60, 60, this);
            } else {
                g.drawImage(img3, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            //让这个炸弹的生命值减少
            bomb.lifeDown();
            //如果bomb 生命值为0，就从集合中删除
            if (bomb.getLife() == 0) {
                bombs.remove(bomb);
            }
        }
        showInfo(g);
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
                g.fill3DRect(x - 10, y + 10, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x - 10, y + 30 + 10, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10 - 10, y + 10 + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20 - 10, y + 10 + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30 - 10, y + 20 + 10, x + 60 - 10, y + 20 + 10);//画出炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x - 10, y + 10, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x - 10, y + 30 + 10, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10 - 10, y + 10 + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20 - 10, y + 10 + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30 - 10, y + 20 + 10, x - 10, y + 20 + 10);//画出炮筒
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

        if ((e.getKeyCode() == KeyEvent.VK_J) && hero.isLive) {
            System.out.println("用户按下了J, 开始射击.");

            hero.shotEnemyTank();

        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void hitHeroTank() {
        //遍历所有的敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历enemyTank 对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断 shot 是否击中我的坦克
                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }
            }
        }
    }

    public void hitTank(Shot shot, Tank enemyTank) {
        //第一次爆炸时，要去调用爆炸的图片，是去调用，而不是去显示出来。提前炸一次
        Bomb b = new Bomb(1000, 1000);
        bombs.add(b);

        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
            if ((shot.x > enemyTank.getX())
                    && (shot.x < (enemyTank.getX() + 40))
                    && (shot.y > enemyTank.getY())
                    && (shot.y < (enemyTank.getY() + 60))) {
                enemyTank.isLive = false;
                shot.isLive = false;
                Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                bombs.add(bomb);
                if(enemyTank!=hero){
                    Recorder.addAllEnemyTankNum();
                }
            }

        } else if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
            if ((shot.x > enemyTank.getX())
                    && (shot.x < (enemyTank.getX() + 60))
                    && (shot.y > enemyTank.getY())
                    && (shot.y < (enemyTank.getY() + 40))) {
                enemyTank.isLive = false;
                shot.isLive = false;
                Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                bombs.add(bomb);
                if(enemyTank!=hero) {
                    Recorder.addAllEnemyTankNum();
                }
            }

        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (hero.shot != null && hero.shot.isLive == true) {
                for (EnemyTank enemyTank : enemyTanks) {
                    for (Shot shot : hero.shots) {
                        hitTank(shot, enemyTank);
                    }


                }
            }
            hitHeroTank();
            this.repaint();
        }
    }
}