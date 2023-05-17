package com.study.tdd.chap07.repository;

import com.study.tdd.chap07.model.User;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
