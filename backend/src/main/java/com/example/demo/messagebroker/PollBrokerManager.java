package com.example.demo.messagebroker;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PollBrokerManager {

    @Autowired
    private final TopicExchange topicExchange;

    @Autowired
    private final AmqpAdmin amqpAdmin;

    public PollBrokerManager(TopicExchange topicExchange, AmqpAdmin amqpAdmin) {
        this.topicExchange = topicExchange;
        this.amqpAdmin = amqpAdmin;
    }

    public void registerQueue(UUID pollId) {
        String queueName = "poll." + pollId.toString();
        Queue queue = new Queue(queueName, true);
        amqpAdmin.declareExchange(topicExchange);
        amqpAdmin.declareQueue(queue);

        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(queueName + ".vote");
        amqpAdmin.declareBinding(binding);
    }
}
