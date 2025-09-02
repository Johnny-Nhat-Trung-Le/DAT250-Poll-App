package com.example.demo.controllers;

import com.example.demo.entities.Poll;
import com.example.demo.entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class PollManager implements Serializable {

    private HashMap<Poll, User> pollUserMap;

    private HashSet<User> users;

    public PollManager() {
        this.pollUserMap = new HashMap<>();
        this.users = new HashSet<>();
    }

    public HashMap<Poll, User> getPollUserMap() {
        return pollUserMap;
    }

    public void setPollUserMap(HashMap<Poll, User> pollUserMap) {
        this.pollUserMap = pollUserMap;
    }

    public HashSet<User> getUsers() { return users; }

    public void setUsers(HashSet<User> users) { this.users = users; }
}
