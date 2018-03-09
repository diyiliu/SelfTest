package com.diyiliu;

import com.diyiliu.thread.DecreaseThread;
import com.diyiliu.thread.IncreaseThread;

/**
 * Description: Test1
 * Author: DIYILIU
 * Update: 2017-07-19 17:09
 */
public class Test1 {

    public static void main(String[] args) {
        Sample sample = new Sample();
        Thread t1 = new IncreaseThread(sample);
        Thread t2 = new DecreaseThread(sample);

        Thread t3 = new IncreaseThread(sample);
        Thread t4 = new DecreaseThread(sample);


        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
