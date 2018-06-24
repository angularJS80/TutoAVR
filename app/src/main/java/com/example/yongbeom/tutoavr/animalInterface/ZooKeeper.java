package com.example.yongbeom.tutoavr.animalInterface;

import com.example.yongbeom.tutoavr.animalInterface.ifs.Predator;

/**
 * Created by yongbeom on 2018. 6. 10..
 */

public class ZooKeeper {
  /*  public void feed(Tiger tiger) {
        System.out.println("feed apple");
    }

    public void feed(Lion lion) {
        System.out.println("feed banana");
    }
*/

    public void feed(Predator predator) {
        //System.out.println("feed apple");

        System.out.println("feed "+predator.getFood());
    }

}
