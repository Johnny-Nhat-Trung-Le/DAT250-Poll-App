package controllers;

import entities.Poll;
import entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

@Component
public class PollManager implements Serializable {

    private HashMap<Poll, User> pollUserMap;

    public PollManager() {
        this.pollUserMap = new HashMap<>();
    }

    public HashMap<Poll, User> getPollUserMap() {
        return pollUserMap;
    }

    public void setPollUserMap(HashMap<Poll, User> pollUserMap) {
        this.pollUserMap = pollUserMap;
    }

}
