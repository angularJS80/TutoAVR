package com.example.yongbeom.tutoavr.animalInterface.animals;

import com.example.yongbeom.tutoavr.animalInterface.Animal;
import com.example.yongbeom.tutoavr.animalInterface.ifs.Predator;

/**
 * Created by yongbeom on 2018. 6. 10..
 */

public class Lion extends Animal implements Predator {
    @Override
    public String getFood() {
        return "apple";
    }
}