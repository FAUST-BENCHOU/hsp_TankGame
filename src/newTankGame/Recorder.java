package newTankGame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int allEnemyTankNum = 0;

    //定义Vector ,指向 MyPanel 对象的 敌人坦克Vector

    private static Vector<EnemyTank> enemyTanks = null;
    private static Vector<Node>nodes=new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //定义IO对象, 准备写数据到文件中
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "";

    static {
        String parentPath = System.getProperty("user.dir");
        recordFile = parentPath + "\\record.txt";
    }

    public static String getRecordFile() {
        return recordFile;
    }

    public static Vector<Node> readFile() throws IOException {

        br=new BufferedReader(new FileReader(recordFile));
        String num = br.readLine();
        if(num!=null&&!num.equals("")) {
            allEnemyTankNum = Integer.parseInt(num);
        }
        String info ="";
        while ((info = br.readLine()) != null) {
            if (!info.trim().equals("")) { // 判断当前行是否为空
                String[] s = info.split(" ");
                if (s.length == 3) {
                    Node node = new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
                    nodes.add(node);
                }
            }
        }
        if (br!=null){
            br.close();
        }
        return nodes;
    }

    public static void keepRecord() throws IOException {
        bw = new BufferedWriter(new FileWriter(recordFile));
        bw.write(allEnemyTankNum + "");
        bw.newLine();
if (enemyTanks!=null) {
    for (int i = 0; i < enemyTanks.size(); i++) {
        //取出敌人坦克
        EnemyTank enemyTank = enemyTanks.get(i);
        if (enemyTank.isLive) { //建议判断.
            //保存该enemyTank信息
            String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
            //写入到文件
            String parentPath = System.getProperty("user.dir");
            recordFile = parentPath + "\\record.txt";
            bw.write(record + "\r\n");

        }
    }
}
            if (bw != null) {
            bw.close();
        }
        }


    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克击毁一个敌人坦克，就应当 allEnemyTankNum++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }

}
