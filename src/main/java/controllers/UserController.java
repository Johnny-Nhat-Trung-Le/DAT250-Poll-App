package controllers;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {

//    private final UserRepository repository;
    private final PollManager pollManager;

    @Autowired
    public UserController(PollManager pollManager) {
//        this.repository = repository;
        this.pollManager = pollManager;
    }

//    @PostMapping
//    public User createUser (User user) {
//        return repository.save(user);
//    }
//
//    @GetMapping
//    public Iterable<User> getAllUsers() {
//        return repository.findAll();
//    }

}
