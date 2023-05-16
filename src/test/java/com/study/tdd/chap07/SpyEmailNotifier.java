package com.study.tdd.chap07;

public class SpyEmailNotifier implements EmailNotifier {

    private boolean called;
    private String email;

    @Override
    public boolean isCalled() {
        return this.called;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void sendRegisterEmail(String email) {
        this.email = email;
        this.called = true;
    }
}
