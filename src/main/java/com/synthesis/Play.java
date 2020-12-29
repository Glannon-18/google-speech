package com.synthesis;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Play {


    public static Player player = null;

    public static void playMp3() {

        try {
            //声明一个File对象
            File mp3 = new File("D:\\output.mp3");

            //创建一个输入流
            FileInputStream fileInputStream = new FileInputStream(mp3);

            //创建一个缓冲流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            //创建播放器对象，把文件的缓冲流传入进去
            player = new Player(bufferedInputStream);

            //注意这里
            new Thread(() -> {
                //调用播放方法进行播放
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }).start();

//            Thread.sleep(10000);
//            player.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        playMp3();
    }
}
