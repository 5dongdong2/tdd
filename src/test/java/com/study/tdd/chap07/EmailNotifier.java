package com.study.tdd.chap07;

public interface EmailNotifier {
    boolean isCalled();

    String getEmail();

    void sendRegisterEmail(String email);
}
