package com.mm.messaging.producer;

import com.mm.messaging.dto.Cat;
import com.mm.messaging.dto.Turtle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatsQueueProducer implements AnimalMessageProducer<Cat> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatsQueueProducer.class);

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routing.cats.key}")
    private String catsRoutingKey;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendAnimalMessage(Cat cat) {
        LOGGER.info("Sending a cat message - " + cat);
        this.amqpTemplate.convertAndSend(exchangeName, catsRoutingKey, cat);
    }
}
