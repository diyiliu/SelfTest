package com.diyiliu.telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Description: DisplayThread
 * Author: DIYILIU
 * Update: 2017-08-16 09:37
 */
public class DisplayThread implements Runnable{

    private InputStream inputStream;

    public DisplayThread(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

    private boolean flag = true;

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String content;
            while (flag && (content = reader.readLine()) != null){
               queue.add(content + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("die...");
    }

    public Queue getContent(){

        return queue;
    }

    public void close(){

        flag = false;
    }
}
