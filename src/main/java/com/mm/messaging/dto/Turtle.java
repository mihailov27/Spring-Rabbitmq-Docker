package com.mm.messaging.dto;

public class Turtle extends AbstractAnimal implements Animal {

    public Turtle() {

    }

    public Turtle(String name, int age) {
        super(name, age);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.REPTILE;
    }

    @Override
    public int getLegsCount() {
        return 4;
    }

    @Override
    public String toString() {
        return String.format("Turtle { name: %s, age: %d, type: %s }", name, age, getType().toString());
    }
}
