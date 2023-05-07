package com.study.tdd.chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(PasswordStrength expectedStrength, String password) {
        assertEquals(expectedStrength, meter.meter(password));
    }

    @Test
    void nullInput_Then_Invalid() {
        assertStrength(PasswordStrength.INVALID, null);
    }

    @Test
    void emptyInput_Then_Invalid() {
        assertStrength(PasswordStrength.INVALID, "");
    }

    @Test
    void meetsAllCriteria_Then_Strong() {
        assertStrength(PasswordStrength.STRONG, "ab12!@AB");
        assertStrength(PasswordStrength.STRONG, "abc1!Add");
    }

    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength(PasswordStrength.NORMAL, "ab12!@A");
        assertStrength(PasswordStrength.NORMAL, "Ab12!c");
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength(PasswordStrength.NORMAL, "ab!@ABqwer");
    }

    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength(PasswordStrength.NORMAL, "ab12!@df");
    }

    @Test
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength(PasswordStrength.WEAK, "abcdefgh");
    }

    @Test
    void meetsOnlyNumberCriteria_Then_Weak() {
        assertStrength(PasswordStrength.WEAK, "12345");
    }

    @Test
    void meetsOnlyUppercaseCriteria_Then_Weak() {
        assertStrength(PasswordStrength.WEAK, "ABCDE");
    }

    @Test
    void meetsNothing_Then_Weak() {
        assertStrength(PasswordStrength.WEAK, "abc");
    }
}
