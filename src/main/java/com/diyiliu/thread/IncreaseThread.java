package com.diyiliu.thread;

import com.diyiliu.Sample;

/**
 * Description: IncreaseThread
 * Author: DIYILIU
 * Update: 2017-07-19 17:06
 */
public class IncreaseThread extends Thread {

    private Sample sample;

    public IncreaseThread(Sample sample) {

        this.sample = sample;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep((int) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sample.increase();
        }
    }
}
