package com.geek.jvm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyClassLoader extends ClassLoader {

    private String fileFullPath;

    public MyClassLoader(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

    /**
    * 注意：name需要类全路径名，即Hello.java的package+Class，否则会抛出异常java.lang.NoClassDefFoundError
    */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(fileFullPath);
        System.out.println(fileFullPath);
        if (!file.exists())
            throw new ClassCastException();

        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(file.toPath());
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) ~bytes[i];//反码处理
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(name, bytes, 0, bytes.length);
    }

    public static void main(String[] args) throws Exception {
        //获取相对路径
        String dir = System.getProperty("user.dir");
        //目标文件全路径
        String fileFullPath = dir + "\\src\\config\\jvm\\static\\Hello.xlass";
 
        //JAVA9之后newInstance方法Deprecated
        // Object hello = new MyClassLoader(fileFullPath).findClass("Hello").newInstance();
        Object hello = new MyClassLoader(fileFullPath).findClass("Hello").getDeclaredConstructor().newInstance();
        
        //反射调用Hello类中的hello方法
        hello.getClass().getMethod("hello").invoke(hello);//Hello, classLoader!
    }
}
