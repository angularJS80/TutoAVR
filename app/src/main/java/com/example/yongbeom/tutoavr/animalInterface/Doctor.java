package com.example.yongbeom.tutoavr.animalInterface;

import com.example.yongbeom.tutoavr.animalInterface.ifs.AnimalAction;

import java.util.List;

/**
 * Created by yongbeom on 2018. 6. 10..
 */

public class Doctor {
    private Animal animal;
    private List<Animal> animals;
    private String target;

    public void goHasp(String target){
        this.target = target;
    }
    
    
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    
    
    
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    


    public void doActionSleeping(){
       System.out.println("now Sleeping animal "+animal.getName());
    }


    public void docAction(AnimalAction animal){
        //System.out.println("start action to "+animal.getName());
        animal.bark();

    }

    public void docAnyAction(Animal animal){
        //System.out.println("start action to "+animal.getName());
        animal.bark();

    }


}
