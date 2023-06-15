package newTankGame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Hero extends Tank {

    public Hero(int x, int y) {
        super(x, y);
    }

    Vector<Shot>shots = new Vector<>();
    Shot shot = null;
    Map<Integer, Image> imgMap = new HashMap<>();
    public void setImgMap(Map<Integer, Image> imgMap) {
        this.imgMap = imgMap;
    }

    private boolean checkImageMap(int start, int end, int y1, int y2) {
        for (int i = start; i <= end; i++) {
            if (!(this.getX() > 120 + 20 * i || this.getX() + 40 < 100 + 20 * i
                    || this.getY() > y2 || this.getY() < y1)) {
                if (imgMap.get(i) != null) {
                    return true;
                }
            }
        }
        return false;
    }
    boolean isTouch(){
       if (this.getDirection()==0||this.getDirection()==2){
           //墙
           for (int i = 0; i <=40; i++) {
               if (!(this.getX()>120+20*i||this.getX()+40<100+20*i||this.getY()>220||this.getY()+60<200)) {
                   if (imgMap.get(i) != null) {
                       return true;
                   }
               }
           }
           for (int i = 1; i <=40; i++) {
               if (!(this.getX()>120+20*i||this.getX()+40<100+20*i||this.getY()>240||this.getY()+60<220)) {
                   if (imgMap.get(i+40) != null) {
                       return true;
                   }
               }
           }
           if (!(this.getX()+40<100||this.getX()>900||this.getY()+60<400||this.getY()>440)){return true;}
       }else {
           //墙
           for (int i = 0; i <=40; i++) {
               if (!(this.getX()+50 < 100+20*i || this.getX() > 120+20*i || this.getY()+50 < 200 || this.getY()+10 > 220) ) {
                   if (imgMap.get(i) != null) {
                       return true;
                   }
               }
           }
           for (int i = 1; i <=40; i++) {
               if (!(this.getX()+50 < 100+20*i || this.getX() > 120+20*i || this.getY()+50 < 220 || this.getY() +10> 240) ) {
                   if (imgMap.get(i+40) != null) {
                       return true;
                   }
               }
           }

           if (!(this.getX()+50<100||this.getX()-20>900||this.getY()+50<400||this.getY()+10>440)){return true;}
       }
       return false;
    }

    public void shotEnemyTank(){
        if (shots.size()>5){
            return;
        }
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
        shots.add(shot);
        new Thread(shot).start();

    }
}
