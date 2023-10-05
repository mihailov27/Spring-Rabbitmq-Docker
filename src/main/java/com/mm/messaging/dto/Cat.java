package com.mm.messaging.dto;

public class Cat extends AbstractAnimal implements Animal {

    public Cat() {

    }

    public Cat(String name, int age) {
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
        return String.format("Cat { name: %s, age: %d, type: %s }", name, age, getType().toString());
    }
}
