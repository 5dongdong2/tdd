package com.study.tdd.chap07.model;

public class User {
    private final String id;
    private final String password;
    private final String email;

    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
