package com.example.demo.entities;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
public class PollManager implements Serializable {

    // userId
    private HashMap<UUID, User> users;
    // pollId
    private HashMap<UUID, Poll> polls;
    // pollId
    private final HashMap<UUID, Set<Vote>> pollVoteMap;

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

    public User findUserById(UUID userId) { return users.get(userId); }

    public Poll findPollById(UUID pollId) { return polls.get(pollId); }

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

    public Set<Vote> getVotes() {
        Set<Vote> allVotes = new HashSet<>();

        for (Set<Vote> votes : pollVoteMap.values()) {
            allVotes.addAll(votes);
        } return allVotes;
    }

    public Set<Vote> findVotesByUserId(UUID userId) {
        Set<Vote> userVotes = new HashSet<>();

        for (Set<Vote> votes : pollVoteMap.values()) {
            for (Vote vote : votes) {
                if (vote.getUserId().equals(userId)) {
                    userVotes.add(vote);
                }
            }
        } return userVotes;
    }

    public Vote findRecentVoteByUserId(UUID userId) {
        Set<Vote> userVotes = findVotesByUserId(userId);
        return userVotes.stream()
                .max(Comparator.comparing(Vote::getPublishedAt))
                .orElse(null);
    }

    public void submitVote(Poll poll, Vote newVote) {
        UUID userId = newVote.getUserId();
        Set<Vote> votes = pollVoteMap.get(poll.getId());
        Vote oldVote = null;
        for (Vote vote : votes) {
            if(vote.getUserId().equals(userId)) {
                oldVote = vote;
                break;
            }
        }

        if (oldVote == null) {
            pollVoteMap.get(poll.getId()).add(newVote);
        } else if (!oldVote.getOption().equals(newVote.getOption())) {

            votes.remove(oldVote);
            votes.add(newVote);
        }
    }

}
