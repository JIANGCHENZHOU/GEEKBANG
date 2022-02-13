package com.geek.conc;

import java.util.concurrent.BrokenBarrierException;

/**
 * 主线程获取子线程返回值
 * 方法三：Thread.join 
 */
public class GetMessage04 implements Runnable {

    private String msg = null;

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        msg = "i am thread";
    }
    
    public String getMessage() {
        return msg;
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        GetMessage03 runnable = new GetMessage03();
        Thread thread = new Thread(runnable);
        thread.start();//启动线程
        // CyclicBarrier barrier = new CyclicBarrier(1, runnable);//3-栅栏 + Runnable
        
        System.out.println("main thread do something...");
        

        
        
        // barrier.await();//3-栅栏
        System.out.println(runnable.getMessage());
        
        System.out.println("main thread exit...");
    }
}