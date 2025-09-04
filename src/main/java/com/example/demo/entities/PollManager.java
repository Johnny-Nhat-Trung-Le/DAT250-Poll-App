package com.example.demo.entities;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Component
public class PollManager implements Serializable {

    private HashMap<UUID, User> users;
    private HashMap<UUID, Poll> polls;
    // pollId, all the votes for that poll
    private HashMap<UUID, Set<Vote>> pollVoteMap;

    /*
    * PollManager hanldes everything storing related and such, everything poll related gets managed here
    * -->
    * The controllers use PollManager and call specific methods to do stuff, get, set, delete and so on
    *
    * with this you dont need to test with response entities or not idk
    * */

    public PollManager() {
        this.users = new HashMap<>();
        this.polls = new HashMap<>();
        this.pollVoteMap = new HashMap<>();
    }

    public Collection<User> getUsers() { return users.values(); }

    public void setUsers(HashMap<UUID, User> users) { this.users = users; }

    public void addUser(User user) { users.put(user.getId(), user); }

    public Collection<Poll> getPolls() { return polls.values(); }

    public void setPolls(HashMap<UUID, Poll> polls) { this.polls = polls; }

    public void addPoll(Poll poll) {
        polls.put(poll.getId(), poll);
        pollVoteMap.put(poll.getId(), new HashSet<>());
    }

    public User findUserById(UUID id) {
        return users.get(id);
    }

    public Poll findPollById(UUID id) {
        return polls.get(id);
    }

    public Collection<Poll> findPollsByUserId(UUID userId) {
        List<Poll> userPolls = new ArrayList<>();
        for (Poll p : polls.values()) {
            if (p.getUserId().equals(userId)) {
                userPolls.add(p);
            }
        } return userPolls;
    }

    public boolean deletePoll(UUID pollId) {
        if (!polls.containsKey(pollId)) {
            return false;
        }
        polls.remove(pollId);
        pollVoteMap.remove(pollId);
        return true;
    }

    public List<Vote> getVotes() {
        List<Vote> allVotes = new ArrayList<>();

        for (Set<Vote> votes : pollVoteMap.values()) {
            allVotes.addAll(votes);
        } return allVotes;
    }

    public List<Vote> findVotesByUserId(UUID userId) {
        List<Vote> userVotes = new ArrayList<>();

        for (Set<Vote> votes : pollVoteMap.values()) {
            for (Vote vote : votes) {
                if (vote.getUserId().equals(userId)) {
                    userVotes.add(vote);
                }
            }
        } return userVotes;
    }

    public Vote findRecentVoteByUserId(UUID userId) {
        List<Vote> userVotes = findVotesByUserId(userId);
        return userVotes.stream()
                .max(Comparator.comparing(Vote::getPublishedAt))
                .orElse(null);
    }

    // evt. public void incrementVoteCount() { voteCount += 1; }
}
