package com.example.demo.controllers;

import com.example.demo.entities.Poll;
import com.example.demo.entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component
public class PollManager implements Serializable {

    // User -- Poll connection
    private HashMap<Poll, User> pollUserMap;
    private HashSet<User> users;
    // Vote -- Users?

    /*
    * PollManager hanldes everything storing related and such
    * -->
    * The controllers use PollManager and call specific methods to do stuff, get, set, delete and so on
    *
    * with this you dont need to test with response entities or not idk
    * */

    public PollManager() {
        this.pollUserMap = new HashMap<>();
        this.users = new HashSet<>();
    }

    public HashMap<Poll, User> getPollUserMap() { return pollUserMap; }

    public void setPollUserMap(HashMap<Poll, User> pollUserMap) { this.pollUserMap = pollUserMap; }

    public HashSet<User> getUsers() { return users; }

    public void setUsers(HashSet<User> users) { this.users = users; }

    public void addUser(User user) { users.add(user); }

    public Set<Poll> getPolls() { return pollUserMap.keySet(); }

    public void addPoll(Poll poll) { pollUserMap.put(poll, poll.getOwner()); }
}
