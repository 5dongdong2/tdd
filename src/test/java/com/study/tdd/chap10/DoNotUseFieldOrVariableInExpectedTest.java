package com.study.tdd.chap10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoNotUseFieldOrVariableInExpectedTest {

    private final List<String> badCharacter = List.of("ㄱr", "ㄴr", "ㄷr", "ㄹr");

    @Test
    @DisplayName("필드를 기대값에 사용하는 경우 - 나쁜 문자열 확인")
    void saveAnswerSuccessfully_badCase() {
        List<String> content = List.of("ㄱr", "ㄴr", "ㄷr", "ㄹr");

        assertAll(
                () -> assertEquals(badCharacter.get(0), content.get(0)),
                () -> assertEquals(badCharacter.get(1), content.get(1)),
                () -> assertEquals(badCharacter.get(2), content.get(2)),
                () -> assertEquals(badCharacter.get(3), content.get(3))
        );
    }

    @Test
    @DisplayName("문자열을 기대값에 사용하는 경우 - 나쁜 문자열 확인")
    void saveAnswerSuccessfully_goodCase() {
        List<String> content = List.of("ㄱr", "ㄴr", "ㄷr", "ㄹr");

        assertAll(
                () -> assertEquals("ㄱr", content.get(0)),
                () -> assertEquals("ㄴr", content.get(1)),
                () -> assertEquals("ㄷr", content.get(2)),
                () -> assertEquals("ㄹr", content.get(3))
        );
    }

    public String formatDate(LocalDate date) {
        return date.getYear() + "년 " + date.getMonthValue() + "월 " + date.getDayOfMonth() + "일";
    }

    @Test
    @DisplayName("변수를 기대값에 사용하는 경우 - LocalDate를 yyyy년 MM월 dd일로 변환")
    void dateFormat_badCase() {
        LocalDate date = LocalDate.of(1945, 8, 15);
        String dateStr = formatDate(date);

        assertEquals(date.getYear() + "년 " + date.getMonthValue() + "월 " + date.getDayOfMonth() + "일", dateStr);
    }

    @Test
    @DisplayName("문자열을 기대값에 사용하는 경우 - LocalDate를 yyyy년 MM월 dd일로 변환")
    void dateFormat_goodCase() {
        LocalDate date = LocalDate.of(1945, 8, 15);
        String dateStr = formatDate(date);

        assertEquals("1945년 8월 15일", dateStr);
    }
}
