package com.mm.messaging.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.messaging.dto.Cat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatsQueueListener extends AbstractListener<Cat> implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatsQueueListener.class);

    @Autowired
    private MessageConverter messageConverter;

    @Override
    public void onMessage(Message message) {
        LOGGER.info("New message received on Cats queue." + message.toString());
        Cat cat = convertFromMessage(message);
        LOGGER.info("Message: " + cat);
    }
}
