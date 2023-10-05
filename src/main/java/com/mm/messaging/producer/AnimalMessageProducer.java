package com.mm.messaging.producer;

import com.mm.messaging.dto.Animal;

public interface AnimalMessageProducer<T extends Animal> {

    void sendAnimalMessage(T animal);

}
