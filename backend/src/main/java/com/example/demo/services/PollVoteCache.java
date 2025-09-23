package com.example.demo.services;

import com.example.demo.entities.Poll;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import redis.clients.jedis.UnifiedJedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PollVoteCache {

    private final UnifiedJedis jedis;
    private final EntityManager entityManager;
    private final int seconds;

    public PollVoteCache(UnifiedJedis jedis, EntityManager entityManager, int seconds) {
        this.jedis = jedis;
        this.entityManager = entityManager;
        this.seconds = seconds;
    }

    private String buildKey(UUID pollId) {
        return "poll:" + pollId + ":votes";
    }

    public Map<Integer, Long> getVoteCounts(Poll poll) {
        String key = buildKey(poll.getId());

        Map<String, String> cachedVoteCounts = jedis.hgetAll(key);

        if (!cachedVoteCounts.isEmpty()) {
            System.out.println("Cache for poll " + poll.getId());
            Map<Integer, Long> result = new HashMap<>();
            cachedVoteCounts.forEach((presentationOrder,voteCount) ->
                    result.put(Integer.parseInt(presentationOrder), Long.parseLong(voteCount)));
            return result;
        }

        System.out.println("Cache missed for poll " + poll.getId());
        Map<Integer, Long> dbVoteCount = loadVoteCountsFromDB(poll.getId());
        if (!dbVoteCount.isEmpty()) {
            setVoteCountToCache(poll.getId(), dbVoteCount);
        }
        return dbVoteCount;
    }

    public Map<Integer, Long> loadVoteCountsFromDB(UUID pollId) {
        String sql = """
                SELECT o.presentationOrder, COUNT(v.id)
                FROM vote_options o
                INNER JOIN votes v on o.id = v.voted_on
                WHERE o.poll = ?
                GROUP BY o.presentationOrder
                ORDER BY o.presentationOrder;
                """;
        TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
        query.setParameter("pollId", pollId);

        List<Object[]> resultList = query.getResultList();
        Map<Integer, Long> dbVoteCount = new HashMap<>();
        for (Object[] row : resultList) {
            dbVoteCount.put((Integer) row[0], (Long) row[1]);
        }
        return dbVoteCount;
    }

    public void setVoteCountToCache(UUID pollId, Map<Integer, Long> dbVoteCount) {
        String key = buildKey(pollId);
        Map<String, String> hashSet = new HashMap<>();
        dbVoteCount.forEach((presentationOrder,voteCount) ->
                hashSet.put(String.valueOf(presentationOrder), String.valueOf(voteCount)));
        jedis.hset(key, hashSet);
        jedis.expire(key, seconds);
    }

    public void incrementVoteCache(UUID pollId, int optionOrder, long number) {
        String key = buildKey(pollId);
        jedis.hincrBy(key, String.valueOf(optionOrder), number);
        jedis.expire(key, seconds);
    }

    public void invalidate(UUID pollId) {
        String key = buildKey(pollId);
        jedis.del(key);
    }
}
