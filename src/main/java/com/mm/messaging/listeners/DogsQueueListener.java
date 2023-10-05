package com.mm.messaging.listeners;

import com.mm.messaging.dto.Cat;
import com.mm.messaging.dto.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class DogsQueueListener extends AbstractListener<Dog> implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DogsQueueListener.class);

    @Override
    public void onMessage(Message message) {
        LOGGER.info("New message received on Dogs queue." + message.toString());
        Dog dog = convertFromMessage(message);
        LOGGER.info("Message: " + dog);
    }
}
