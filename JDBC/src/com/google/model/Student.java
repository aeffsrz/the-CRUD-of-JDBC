package com.google.model;

public class Student {

    private String id;
    private int age;
    private String name;

    public Student(String id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "(" +
                "id=" + id +
                ", age=" + age +
                ", name=" + name +
                ')';
    }
}



