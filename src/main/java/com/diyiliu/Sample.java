package com.diyiliu;

/**
 * Description: Sample
 * Author: DIYILIU
 * Update: 2017-07-19 17:02
 */
public class Sample {

    private int number;

    public synchronized void increase(){

        // 线程醒来后 需要重新判断
        while (number != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        number++;

        System.out.println(number);

        notifyAll();
    }

    public synchronized void decrease(){

        while (number == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        number--;

        System.out.println(number);

        notifyAll();
    }
}
