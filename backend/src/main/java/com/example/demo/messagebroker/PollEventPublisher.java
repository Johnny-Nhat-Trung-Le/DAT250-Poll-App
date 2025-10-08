package com.example.demo.messagebroker;

import com.example.demo.entities.Vote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PollEventPublisher {

    @Autowired
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    private final ObjectMapper objectMapper;

    public PollEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void publishVote(String pollId, Vote vote) {
        try {
            String message = objectMapper.writeValueAsString(vote);
            String routing = "poll." + pollId + ".vote";
            rabbitTemplate.convertAndSend("poll-exchange", routing, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
