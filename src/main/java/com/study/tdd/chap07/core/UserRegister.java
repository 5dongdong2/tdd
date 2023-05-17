package com.study.tdd.chap07.core;

import com.study.tdd.chap07.checker.WeakPasswordChecker;
import com.study.tdd.chap07.exception.DuplicatedIdException;
import com.study.tdd.chap07.exception.WeakPasswordException;
import com.study.tdd.chap07.model.User;
import com.study.tdd.chap07.notifier.EmailNotifier;
import com.study.tdd.chap07.repository.UserRepository;

import java.util.Objects;

public class UserRegister {
    private final WeakPasswordChecker weakPasswordChecker;
    private final UserRepository userRepository;
    private final EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker weakPasswordChecker, UserRepository userRepository, EmailNotifier emailNotifier) {
        this.weakPasswordChecker = weakPasswordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
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

        emailNotifier.sendRegisterEmail(email);
    }
}
