package com.example.yongbeom.tutoavr.animalInterface.animals;

import com.example.yongbeom.tutoavr.animalInterface.Animal;
import com.example.yongbeom.tutoavr.animalInterface.ifs.AnimalAction;

/**
 * Created by yongbeom on 2018. 6. 10..
 */

public class Dog extends Animal implements AnimalAction{

    @Override
    public void bark2() {
        System.out.println("Dog Class mongmong");
    }
}
