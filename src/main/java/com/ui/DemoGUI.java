package com.ui;

import javax.swing.*;
import java.awt.*;

public class DemoGUI extends JPanel {
    public void init(){
        Demo jfm = new Demo();
//        JFrame jfm=new JFrame();
        this.setDoubleBuffered(true);//这个就是JPnel自带的双缓冲技术
        jfm.add(this);//把JPanel组件加到窗口上来
        jfm.setVisible(true);
        jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfm.setBounds(0, 8, 820, 620);
        jfm.setLocationRelativeTo(null);
        jfm.setResizable(false);
        new Thread(new MyThread()).start();
    }
    public static void main(String[] args) {
        new DemoGUI().init();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.drawRect(20, 40, 750, 525);
        g.fillRect(200, 110, 50,50);
        for(int i=1;i<=50;i++){
            for(int j=1;j<=35;j++){
                g.drawLine(20,15*j+40,770, 15*j+40);
            }
            g.drawLine(20+15*i, 40, 20+15*i, 565);
        }
    }
    public class MyThread implements Runnable{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
