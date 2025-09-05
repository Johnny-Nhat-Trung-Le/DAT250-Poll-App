package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class User implements Serializable {

    private UUID id;
    private String username;
    private String email;

    public User(String username, String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
    }

}
