package com.mm.messaging.core;

import com.mm.messaging.listeners.CatsQueueListener;
import com.mm.messaging.listeners.DogsQueueListener;
import com.mm.messaging.listeners.TurtlesQueueListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.port}")
    private Integer port;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("${rabbitmq.exchange}")
    private String exchangeName;
    @Value("${rabbitmq.queue.dogs}")
    private String dogsQueue;
    @Value("${rabbitmq.routing.dogs.key}")
    private String dogsRoutingKey;
    @Value("${rabbitmq.queue.cats}")
    private String catsQueue;
    @Value("${rabbitmq.routing.cats.key}")
    private String catsRoutingKey;
    @Value("${rabbitmq.queue.turtles}")
    private String turtlesQueue;
    @Value("${rabbitmq.routing.turtles.key}")
    private String turtlesRoutingKey;

    // Exchange

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    // Queues

    @Bean
    public Queue dogsQueue() {
        return new Queue(dogsQueue, true);
    }

    @Bean
    public Queue catsQueue() {
        return new Queue(catsQueue, true);
    }

    @Bean
    public Queue turtlesQueue() {
        return new Queue(turtlesQueue, true);
    }

    @Bean
    public Binding bindingDogsQueue(Queue dogsQueue) {
        return BindingBuilder
                .bind(dogsQueue)
                .to(exchange())
                .with(dogsRoutingKey);
    }

    // Bindings

    @Bean
    public Binding bindingCatsQueue(Queue catsQueue) {
        return BindingBuilder
                .bind(catsQueue)
                .to(exchange())
                .with(catsRoutingKey);
    }

    @Bean
    public Binding bindingTurtlesQueue(Queue turtlesQueue) {
        return BindingBuilder
                .bind(turtlesQueue)
                .to(exchange())
                .with(turtlesRoutingKey);
    }

    // Message converter

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Connection Factory

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }


    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    // Listener Containers

    @Bean
    SimpleMessageListenerContainer dogsQueueMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                  DogsQueueListener dogsQueueListener) {
        SimpleMessageListenerContainer dogsContainer = new SimpleMessageListenerContainer();
        dogsContainer.setConnectionFactory(connectionFactory);
        dogsContainer.setQueueNames(dogsQueue);
        dogsContainer.setMessageListener(dogsQueueListener);
        dogsContainer.setChannelTransacted(true);
        return dogsContainer;
    }

    @Bean
    SimpleMessageListenerContainer catsQueueMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                     CatsQueueListener catsQueueListener) {
        SimpleMessageListenerContainer catsContainer = new SimpleMessageListenerContainer();
        catsContainer.setConnectionFactory(connectionFactory);
        catsContainer.setQueueNames(catsQueue);
        catsContainer.setMessageListener(catsQueueListener);
        catsContainer.setChannelTransacted(true);
        return catsContainer;
    }

    @Bean
    SimpleMessageListenerContainer turtlesQueueMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                        TurtlesQueueListener turtlesQueueListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(turtlesQueue);
        container.setMessageListener(turtlesQueueListener);
        container.setChannelTransacted(true);
        return container;
    }
}
