package com.study.tdd.chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String password) {
        if (password == null || password.isEmpty()) {
            return PasswordStrength.INVALID;
        }

        int metCount = getMetCriteriaCount(password);

        if (metCount <= 1) return PasswordStrength.WEAK;
        if (metCount == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCount(String password) {
        int metCount = 0;
        if (password.length() >= 8) metCount++;
        if (containsNumber(password)) metCount++;
        if (containsUppercase(password)) metCount++;
        return metCount;
    }

    private boolean containsNumber(String password) {
        for (char ch : password.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean containsUppercase(String password) {
        for (char ch : password.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                return true;
            }
        }
        return false;
    }
}