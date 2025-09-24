package com.example.demo.entities;

import lombok.Setter;
import org.springframework.stereotype.Component;
import redis.clients.jedis.UnifiedJedis;

import java.io.Serializable;
import java.util.*;

@Component
@Setter
public class PollManager implements Serializable {

    // userId
    private HashMap<UUID, User> users;
    // pollId
    private HashMap<UUID, Poll> polls;
    // pollId
    private final HashMap<UUID, Set<Vote>> pollVoteMap;

    private final UnifiedJedis jedis;

    public PollManager() {
        this.users = new HashMap<>();
        this.polls = new HashMap<>();
        this.pollVoteMap = new HashMap<>();
        this.jedis = new UnifiedJedis("redis://localhost:6379");
    }

    public Collection<User> getUsers() { return users.values(); }

    public User addUser(User user) {
        Collection<User> allCurrentUsers = getUsers();
        for (User u : allCurrentUsers) {
            if (u.getEmail().equals(user.getEmail())){
                return null;
            }
        }
        users.put(user.getId(), user);
        return user;
    }

    public User loginUser(User user) {
        Collection<User> allCurrentUsers = getUsers();
        for (User u : allCurrentUsers) {
            if (u.getUsername().equals(user.getUsername()) && u.getEmail().equals(user.getEmail())){
                return u;
            }
        }
        return null;
    }

    public Collection<Poll> getPolls() { return polls.values(); }

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

    public boolean deletePoll(UUID userId, UUID pollId) {
        if (!(users.containsKey(userId) && polls.containsKey(pollId))) return false;
        Poll poll = findPollById(pollId);
        if (!poll.getUserId().equals(userId)) return false;
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
                if (vote.getId().equals(userId)) {
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

    private void incrementVoteCount(Poll poll, Vote vote) {
        Map<Integer, Long> mapCount = poll.getVoteCount();
        VoteOption voteOption = vote.getVotesOn();
        mapCount.merge(voteOption.getPresentationOrder(), 1L, Long::sum);
    }

    public void submitVote(Poll poll, Vote newVote) {
        UUID userId = newVote.getId();
        if (userId == null) {
            return;
        }
        pollVoteMap.putIfAbsent(poll.getId(), new HashSet<>());
        Set<Vote> votes = pollVoteMap.get(poll.getId());
        Vote oldVote = null;
        for (Vote vote : votes) {
            if(vote.getId().equals(userId)) {
                oldVote = vote;
                break;
            }
        }

        if (oldVote == null) {
            pollVoteMap.get(poll.getId()).add(newVote);
            incrementVoteCount(poll, newVote);
        } else if (!oldVote.getVotesOn().equals(newVote.getVotesOn())) {
            poll.getVoteCount().merge(oldVote.getVotesOn().getPresentationOrder(), -1L, Long::sum);
            poll.getVoteCount().merge(newVote.getVotesOn().getPresentationOrder(), 1L, Long::sum);

            votes.remove(oldVote);
            votes.add(newVote);
        }
    }

}
