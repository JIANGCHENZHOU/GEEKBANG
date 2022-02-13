package com.rexxar.week05.pojo;

public class Student {


    private int id;
    private String name;

    public Student(int id, String name) {
        id = this.id;
        name = this.name;
    }

    public void init(){
        System.out.println("hello...........");
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name;
    }


}
