package com.yqh;

public class Student {
    private String name;
    private String sid;
    private String address;
    private int age;

    public Student(){}
    public Student(String sid, String name, String address, int age){
        this.name = name;
        this.sid = sid;
        this.address = address;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getSid() {
        return sid;
    }
}
