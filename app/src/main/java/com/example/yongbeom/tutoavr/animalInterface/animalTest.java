package com.example.yongbeom.tutoavr.animalInterface;

import com.example.yongbeom.tutoavr.animalInterface.animals.Lion;
import com.example.yongbeom.tutoavr.animalInterface.animals.Tiger;

/**
 * Created by yongbeom on 2018. 6. 10..
 */

public class animalTest {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = new ZooKeeper();

        Tiger tiger = new Tiger();
        Lion lion = new Lion();
        //zooKeeper.feed(tiger);
        //zooKeeper.feed(lion);

        tiger = new Tiger();
        zooKeeper.feed(tiger);

        lion = new Lion();
        zooKeeper.feed(tiger);
    }
}
