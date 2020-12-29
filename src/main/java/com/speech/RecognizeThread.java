package com.speech;

public class RecognizeThread extends Thread {
    private String lang = "zh";

    public RecognizeThread(String lang) {
        this.lang = lang;
        System.out.println("实例化RecognizeThread,语言" + lang);
    }

    public void run() {
        try {
            InfiniteStreamRecognize.infiniteStreamingRecognize(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
