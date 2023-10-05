package com.mm.messaging.producer;

import com.mm.messaging.dto.Turtle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TurtlesQueueProducer implements AnimalMessageProducer<Turtle> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurtlesQueueProducer.class);

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routing.turtles.key}")
    private String turtlesRoutingKey;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public void sendAnimalMessage(Turtle turtle) {
        LOGGER.info("Sending a turtle message - " + turtle);
        amqpTemplate.convertAndSend(exchangeName, turtlesRoutingKey, turtle);
    }
}
