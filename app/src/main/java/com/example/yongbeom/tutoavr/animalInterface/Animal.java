package com.example.yongbeom.tutoavr.animalInterface;

/**
 * Created by yongbeom on 2018. 6. 10..
 */

public class Animal {
    String name;
    int age;
    String bark;

    public void setBark(String bark) {
        this.bark = bark;
    }

    public String getBark() {

        return bark;
    }

    public void bark(){
        System.out.println(bark);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}