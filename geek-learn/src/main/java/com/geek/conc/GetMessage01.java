package com.geek.conc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 *   主线程获取子线程返回值
 *   方法一：Thread(可以改写为Runnable) + 信号量 + 引用对象
 */
public class GetMessage01 extends Thread {
    CountDownLatch latch;
    Semaphore semaphore;
    List<String> msgPool;

    //门栓方式
    GetMessage01(CountDownLatch latch, List<String> msgPool) {
        this.latch = latch;
        this.msgPool = msgPool;
    }

    //信号量方式
    GetMessage01(Semaphore semaphore, List<String> msgPool) {
        this.semaphore = semaphore;
        this.msgPool = msgPool;
    }

    public GetMessage01() {
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        msgPool.add("i am thread");
        //解除独占
        if(latch != null) {
            latch.countDown();
        }
        
        if(semaphore != null) {
            semaphore.release();
        }
    }
    

    public static void main(String[] args) throws InterruptedException {
        // CountDownLatch latch = new CountDownLatch(1);//门闩
        Semaphore semaphore = new Semaphore(1);
        List<String> msgPool = new ArrayList<>();

        // new GetMessage01(latch, msgPool).start();
        
        semaphore.acquireUninterruptibly();//独占
        new GetMessage01(semaphore, msgPool).start();
        System.out.println("main thread do something...");
        
        // latch.await();//等待count=0
        
        semaphore.acquireUninterruptibly();//等待解锁后，再次独占

        System.out.println("msg:" + msgPool.get(0));
        semaphore.release();

        System.out.println("main thread exit...");
    }
}
