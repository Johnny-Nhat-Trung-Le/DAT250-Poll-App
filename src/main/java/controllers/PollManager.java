package controllers;

import entities.Poll;
import entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

@Component
public class PollManager implements Serializable {

    private HashMap<User, Poll> pollUserMap;

    public PollManager() {
        this.pollUserMap = new HashMap<>();
    }

    public HashMap<User, Poll> getPollUserMap() {
        return pollUserMap;
    }

    public void setPollUserMap(HashMap<User, Poll> pollUserMap) {
        this.pollUserMap = pollUserMap;
    }

}
