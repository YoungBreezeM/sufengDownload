package com.fw.domain;

public class TestThread implements Runnable {
    @Override
    public void run() {
        try {
            int i = 0;
            while (i<5){
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
