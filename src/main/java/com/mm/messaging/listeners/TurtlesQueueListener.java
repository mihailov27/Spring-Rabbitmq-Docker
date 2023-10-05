package com.mm.messaging.listeners;

import com.mm.messaging.dto.Dog;
import com.mm.messaging.dto.Turtle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class TurtlesQueueListener extends AbstractListener<Turtle> implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurtlesQueueListener.class);

    @Override
    public void onMessage(Message message) {
        LOGGER.info("New message received on Turtles queue." + message.toString());
        Turtle turtle = convertFromMessage(message);
        LOGGER.info("Message: " + turtle);
    }
}
