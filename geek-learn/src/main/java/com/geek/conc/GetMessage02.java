package com.geek.conc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 主线程获取子线程返回值
 * 方法二：Future + Callable 
 */
public class GetMessage02 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService exe = Executors.newSingleThreadExecutor();
        
        Future<String> result = (Future<String>) exe.submit(() -> {
            Thread.sleep(100);
            return "i am thread";
        });

        System.out.println("main thread do something...");
        System.out.println("msg:" + result.get());
        System.out.println("main thread exit...");
        
    }
}
