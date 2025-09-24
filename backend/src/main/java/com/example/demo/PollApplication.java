package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.UnifiedJedis;

@SpringBootApplication
@RestController
@EnableCaching
public class PollApplication {
	public static void main(String[] args) {
		SpringApplication.run(PollApplication.class, args);

		// Redis Set experiment
//		UnifiedJedis jedis = new UnifiedJedis("redis://localhost:6379");
//		jedis.del("users");
//		jedis.sadd("users", "alice");
//		jedis.sadd("users", "bob");
//		jedis.srem("users", "alice");
//		jedis.sadd("users", "eve");
//		System.out.println("Currently logged in users: " + jedis.smembers("users"));
//		jedis.close();
	}
}