package com.mm.messaging.dto;

public class Dog extends AbstractAnimal implements Animal {


    public Dog() {

    }
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.MAMMAL;
    }

    @Override
    public int getLegsCount() {
        return 4;
    }

    @Override
    public String toString() {
        return String.format("Dog { name: %s, age: %d, type: %s }", name, age, getType().toString());
    }
}
