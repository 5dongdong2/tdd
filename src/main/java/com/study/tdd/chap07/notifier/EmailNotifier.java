package com.study.tdd.chap07.notifier;

public interface EmailNotifier {
    boolean isCalled();

    String getEmail();

    void sendRegisterEmail(String email);
}
