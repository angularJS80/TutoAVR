package com.example.yongbeom.tutoavr.animalInterface.animals;

import com.example.yongbeom.tutoavr.animalInterface.Animal;
import com.example.yongbeom.tutoavr.animalInterface.ifs.AnimalAction;

/**
 * Created by yongbeom on 2018. 6. 10..
 */

public class Cat extends Animal implements AnimalAction{

    @Override
    public void bark2() {
        System.out.println("Cat Class maowmaow");
    }

    public void graw(){
        System.out.println("cat is graw");
    }
}
