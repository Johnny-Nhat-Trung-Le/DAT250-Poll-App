package com.example.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable {

    private String username;
    private String email;

    private Set<Vote> votes;

    public User() {}

    // id?, int? or Long?, probably Long no?
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.votes = new HashSet<>();
    }

    // make getters and setters for everything, essentially

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Set<Vote> getVotes() { return votes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        User user = (User) o;
        return username.equals(user.username) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

}
