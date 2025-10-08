package com.example.demo.messagebroker;

import com.example.demo.entities.Poll;
import com.example.demo.entities.PollManager;
import com.example.demo.entities.Vote;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PollEventListener {

    @Autowired
    private final ObjectMapper objectMapper;
    @Autowired
    private final ConnectionFactory connectionFactory;

    public PollEventListener(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        this.objectMapper = new ObjectMapper();
    }

    public void registerListener(UUID pollId, PollManager pollManager) {
        String queueName = "poll." + pollId.toString();
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(message -> {
            try {
                String body = new String(message.getBody());
                Vote vote = objectMapper.readValue(body, Vote.class);
                Poll poll = pollManager.findPollById(pollId);
                if (poll != null) {
                    pollManager.submitVote(poll, vote);
                } else {
                    System.out.println("No poll found!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        container.start();
    }


}
