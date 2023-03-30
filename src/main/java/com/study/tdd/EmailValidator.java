package com.study.tdd;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_PATTERN_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern;

    public EmailValidator() {
        this.pattern = Pattern.compile(EMAIL_PATTERN_REGEX);
    }

    public boolean validate(final String email) {
        return pattern.matcher(email).matches();
    }
}
