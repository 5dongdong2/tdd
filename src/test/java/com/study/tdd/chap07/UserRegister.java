package com.study.tdd.chap07;

public class UserRegister {
    private final WeakPasswordChecker weakPasswordChecker;

    public UserRegister(WeakPasswordChecker weakPasswordChecker) {
        this.weakPasswordChecker = weakPasswordChecker;
    }

    public void register(String id, String password, String email) {
        if (weakPasswordChecker.checkPasswordWeak(password)) {
            throw new WeakPasswordException();
        }
    }
}
