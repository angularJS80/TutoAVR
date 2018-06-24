package com.example.yongbeom.tutoavr.animalInterface;

import com.example.yongbeom.tutoavr.animalInterface.animals.Cat;
import com.example.yongbeom.tutoavr.animalInterface.animals.Dog;
import com.example.yongbeom.tutoavr.animalInterface.animals.Snake;
import com.example.yongbeom.tutoavr.animalInterface.ifs.AnimalAction;

/**
 * Created by yongbeom on 2018. 6. 10..
 */

public class AnimalStartTest {
    public static void main(String[] args) {
    Cat cat = new Cat();
        cat.setName("kitty");
        cat.setAge(3);
        cat.setBark("set maewmaew");




        Dog dog = new Dog();
        dog.setName("dalgun");
        dog.setAge(10);
        dog.setBark("set mongmong");


        Doctor doctor = new Doctor();
        doctor.docAction(cat);

        AnimalAction actDog = new Dog();
        doctor.docAction(actDog);
        doctor.docAction(dog);


        doctor.docAnyAction(new Animal());

        Snake snake = new Snake();




        Doctor doctor1 = new Doctor();
        doctor1.setAnimal(cat);

        Doctor doctor2 = new Doctor();
        doctor2.setAnimal(dog);


// When 12:00
        doctor1.doActionSleeping();
        doctor2.doActionSleeping();



    }





}
