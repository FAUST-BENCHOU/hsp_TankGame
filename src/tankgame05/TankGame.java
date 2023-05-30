package tankgame05;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

public class TankGame extends JFrame {
    private int num=1;



    MyPanel mp=null;
    public static void main(String[] args) throws IOException {

        new TankGame();
    }
    public TankGame() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("继续游戏（0）or新游戏（1）:");
        while (true) {
            num = scanner.nextInt();
            if (num == 0 || num == 1) {
                break;
            }
            System.out.println("请重输..");
        }
        System.out.println(num);
        mp=new MyPanel(num+"");
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把面板(就是游戏的绘图区域)
        this.setSize(1300, 750);
        this.addKeyListener(mp);//让JFrame 监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame 中增加相关关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Recorder.keepRecord();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}
