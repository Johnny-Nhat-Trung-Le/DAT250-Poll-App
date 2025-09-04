package com.example.demo.entities;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    private UUID id;
    private String username;
    private String email;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.id = UUID.randomUUID();
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public UUID getId() { return id; }

}
