package com.mm.messaging.schedulers;

import com.mm.messaging.dto.Cat;
import com.mm.messaging.dto.Dog;
import com.mm.messaging.dto.Turtle;
import com.mm.messaging.listeners.DogsQueueListener;
import com.mm.messaging.producer.CatsQueueProducer;
import com.mm.messaging.producer.DogsQueueProducer;
import com.mm.messaging.producer.TurtlesQueueProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final String[] names = new String [] { "Tom", "Jack", "Ben", "Gareth", "Michael" };

    @Autowired
    private TurtlesQueueProducer turtlesQueueProducer;

    @Autowired
    private DogsQueueProducer dogsQueueProducer;

    @Autowired
    private CatsQueueProducer catsQueueProducer;

    @Scheduled(fixedRate = 300)
    public void sendTurtle() {
        Random rand = new Random();
        int queueIndex = rand.nextInt(3);
        int nameIndex = rand.nextInt(5);
        int age = rand.nextInt(10);


        if (queueIndex == 0) {
            Turtle turtle = new Turtle(names[nameIndex], age);
            turtlesQueueProducer.sendAnimalMessage(turtle);
        } else if (queueIndex == 1) {
            Cat cat = new Cat(names[nameIndex], age);
            catsQueueProducer.sendAnimalMessage(cat);
        } else {
            Dog dog = new Dog(names[nameIndex], age);
            dogsQueueProducer.sendAnimalMessage(dog);
        }

    }
}
