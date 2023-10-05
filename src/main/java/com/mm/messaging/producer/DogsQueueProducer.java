package com.mm.messaging.producer;


import com.mm.messaging.dto.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DogsQueueProducer implements AnimalMessageProducer<Dog> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DogsQueueProducer.class);

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routing.dogs.key}")
    private String dogsRoutingKey;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendAnimalMessage(Dog dog) {
        LOGGER.info("Sending a dog message - " + dog);
        amqpTemplate.convertAndSend(exchangeName, dogsRoutingKey, dog);
    }
}
