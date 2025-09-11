package com.example.demo;

import com.example.demo.entities.Poll;
import com.example.demo.entities.User;
import com.example.demo.entities.Vote;
import com.example.demo.entities.VoteOption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PollApplicationTests {

	@LocalServerPort
	private int port;

	private RestClient client;

	@Autowired
	void setClientPort(RestClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:" + port).build();
	}

	@Test
	void contextLoads() {
	}

	 @Test
	 void testScenario() {
	 	// Step 1: Create user1
	 	User user1 = new User("Alice", "email");
	 	ResponseEntity<User> response1 = client.post()
	 			.uri("/users")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.body(user1)
	 			.retrieve()
	 			.toEntity(User.class);

	 	assertNotNull(response1.getBody());
	 	assertEquals("Alice", response1.getBody().getUsername());
	 	assertEquals("email", response1.getBody().getEmail());

	 	// Step 2: List all users and check if user1 is in the list
	 	ResponseEntity<Collection<User>> response2 = client.get()
	 			.uri("/users")
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<User>>() {});

	 	assertNotNull(response2.getBody());
	 	Collection<User> users1 = response2.getBody();
	 	assertTrue(users1.contains(user1));

	 	// Step 3: Create user2
	 	User user2 = new User("Bob", "email69");
	 	ResponseEntity<User> response3 = client.post()
	 			.uri("/users")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.body(user2)
	 			.retrieve()
	 			.toEntity(User.class);

	 	assertNotNull(response3.getBody());
	 	assertEquals("Bob", response3.getBody().getUsername());
	 	assertEquals("email69", response3.getBody().getEmail());

	 	// Step 4: List all users and check if both user1 and user2 are there
	 	ResponseEntity<Collection<User>> response4 = client.get()
	 			.uri("/users")
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<User>>() {});

	 	assertNotNull(response4.getBody());
	 	Collection<User> users2 = response4.getBody();
	 	assertTrue(users2.contains(user1));
	 	assertTrue(users2.contains(user2));

	 	// Step 5: user1 creates a poll
	 	String question = "Animal?";
	 	VoteOption voteOption1 = new VoteOption(1, "Rat");
	 	VoteOption voteOption2 = new VoteOption(2,"Cat");
	 	VoteOption voteOption3 = new VoteOption(3, "Bat");
	 	ArrayList<VoteOption> voteOptions = new ArrayList<>(Arrays.asList(voteOption1,voteOption2,voteOption3));
	 	Instant publishedAt = Instant.now();
	 	Instant validUntil = Instant.now().plus(1, ChronoUnit.HOURS);
	 	Poll poll = new Poll(question, voteOptions,publishedAt,validUntil, user1.getId());

	 	ResponseEntity<Poll> response5 = client.post()
	 			.uri("/polls")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.body(poll)
	 			.retrieve()
	 			.toEntity(Poll.class);

	 	assertEquals(HttpStatus.OK, response5.getStatusCode());
	 	assertNotNull(response5.getBody());
	 	assertNotNull(response5.getBody().getId());
	 	assertEquals(user1.getId(), response5.getBody().getUserId());
	 	assertEquals(question, response5.getBody().getQuestion());
	 	assertEquals(voteOptions, response5.getBody().getOptions());

	 	// Step 6: List all polls and check if the poll is in it
	 	ResponseEntity<Collection<Poll>> response6 = client.get()
	 			.uri("/polls")
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<Poll>>() {});

	 	assertNotNull(response6.getBody());
	 	Collection<Poll> polls = response6.getBody();
	 	assertTrue(polls.contains(poll));

	 	// Step 7: user2 votes on user1's poll
	 	Vote oldVote = new Vote(user2.getId(), voteOption2);

	 	ResponseEntity<Void> responseVote1 = client.post()
	 			.uri("/polls/{pollId}/vote", poll.getId())
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.body(oldVote)
	 			.retrieve()
	 			.toBodilessEntity();

	 	assertEquals(HttpStatus.OK, responseVote1.getStatusCode());

	 	URI uriPoll = UriComponentsBuilder
	 			.fromUriString("http://localhost:" + port + "/polls?q={q}")
	 			.build("userId", user1.getId());

	 	ResponseEntity<Collection<Poll>> responsePoll1 = client.get()
	 			.uri(uriPoll)
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<Poll>>() {});

	 	assertNotNull(responsePoll1.getBody());

	 	LinkedList<Poll> linkedList1 = new LinkedList<>(responsePoll1.getBody());
	 	Poll pollOldVote = linkedList1.pop();
	 	assertEquals(1L, pollOldVote.getVoteCount().get(voteOption2.getId()));


	 	URI uriOldVote = UriComponentsBuilder
	 			.fromUriString("http://localhost:" + port + "/votes?q={q}")
	 			.build("userId", user2.getId());

	 	ResponseEntity<Collection<Vote>> response7 = client.get()
	 			.uri(uriOldVote)
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<Vote>>() {});

	 	assertEquals(HttpStatus.OK, response7.getStatusCode());
	 	assertNotNull(response7.getBody());
	 	assertTrue(response7.getBody().contains(oldVote));

	 	VoteOption user2voteOption1 = null;
	 	for (Vote v : response7.getBody()) {
	 		if (v.equals(oldVote)) {
	 			user2voteOption1 = v.getOption();
	 			break;
	 		}
	 	}

	 	assertNotNull(user2voteOption1);
	 	assertEquals(user2voteOption1, oldVote.getOption());

	 	// Step 8: user2 changes vote
	 	Vote newVote = new Vote(user2.getId(), voteOption1);

	 	ResponseEntity<Void> responseVote2 = client.post()
	 			.uri("/polls/{pollId}/vote", poll.getId())
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.body(newVote)
	 			.retrieve()
	 			.toBodilessEntity();

	 	assertEquals(HttpStatus.OK, responseVote2.getStatusCode());

	 	ResponseEntity<Collection<Poll>> responsePoll2 = client.get()
	 			.uri(uriPoll)
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<Poll>>() {});

	 	assertNotNull(responsePoll2.getBody());

	 	URI uriNewVote = UriComponentsBuilder
	 			.fromUriString("http://localhost:" + port + "/votes?q={q}")
	 			.build("userId", user2.getId());

	 	ResponseEntity<Collection<Vote>> response8 = client.get()
	 			.uri(uriNewVote)
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<Vote>>() {});

	 	assertEquals(HttpStatus.OK, response8.getStatusCode());
	 	assertNotNull(response8.getBody());
	 	assertTrue(response8.getBody().contains(newVote));
	 	assertFalse(response8.getBody().contains(oldVote));

	 	Instant publishedAtVote = null;
	 	Vote votePublishedNow = null;
	 	for (Vote v : response8.getBody()) {
	 		if (v.equals(newVote)) {
	 			publishedAtVote = v.getPublishedAt();
	 			votePublishedNow = v;
	 			assertNotEquals(v.getOption(),user2voteOption1);
	 			break;
	 		}
	 	}

	 	LinkedList<Poll> linkedList2 = new LinkedList<>(responsePoll2.getBody());
	 	Poll pollNewVote = linkedList2.pop();
	 	assertEquals(0L, pollNewVote.getVoteCount().get(voteOption2.getId()));
	 	assertEquals(1L, pollNewVote.getVoteCount().get(voteOption1.getId()));

	 	// Step 9: List most recent vote for user2
	 	ResponseEntity<Vote> response9 = client.get()
	 			.uri("/votes/{userId}/recent", user2.getId())
	 			.retrieve()
	 			.toEntity(Vote.class);

	 	assertEquals(HttpStatus.OK, response9.getStatusCode());
	 	assertNotNull(response9.getBody());
	 	assertEquals(votePublishedNow, response9.getBody());
	 	assertEquals(publishedAtVote, response9.getBody().getPublishedAt());

	 	// Step 10: Delete poll made by user1
	 	ResponseEntity<Void> response10 = client.delete()
	 			.uri("/users/{userId}/{pollId}/delete", user1.getId(), poll.getId())
	 			.retrieve()
	 			.toBodilessEntity();

	 	assertEquals(HttpStatus.OK, response10.getStatusCode());

	 	ResponseEntity<Collection<Poll>> responsePoll3 = client.get()
	 			.uri("/polls")
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<Poll>>() {});

	 	assertEquals(HttpStatus.OK, responsePoll3.getStatusCode());
	 	assertNotNull(responsePoll3.getBody());
	 	assertTrue(responsePoll3.getBody().isEmpty());

	 	// Step 11: List all votes
	 	ResponseEntity<Collection<Vote>> response11 = client.get()
	 			.uri("/votes")
	 			.retrieve()
	 			.toEntity(new ParameterizedTypeReference<Collection<Vote>>() {});

	 	assertEquals(HttpStatus.OK, response11.getStatusCode());
	 	assertNotNull(response11.getBody());
	 	assertTrue(response11.getBody().isEmpty());

	 }

}
