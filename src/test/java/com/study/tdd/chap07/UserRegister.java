package com.study.tdd.chap07;

import java.util.Objects;

public class UserRegister {
    private final WeakPasswordChecker weakPasswordChecker;
    private final UserRepository userRepository;

    public UserRegister(WeakPasswordChecker weakPasswordChecker, UserRepository userRepository) {
        this.weakPasswordChecker = weakPasswordChecker;
        this.userRepository = userRepository;
    }

    public void register(String id, String password, String email) {
        if (weakPasswordChecker.checkPasswordWeak(password)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (Objects.nonNull(user)) {
            throw new DuplicatedIdException();
        }
        userRepository.save(new User(id, password, email));
    }
}
