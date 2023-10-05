package com.mm.messaging.listeners;

import com.mm.messaging.dto.Animal;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractListener<T extends Animal> {

    @Autowired
    protected MessageConverter messageConverter;

    protected final T convertFromMessage(Message message) {
        Object obj = messageConverter.fromMessage(message);
        return convertInstance(obj);
    }

    private static <T> T convertInstance(Object o) {
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }


}
