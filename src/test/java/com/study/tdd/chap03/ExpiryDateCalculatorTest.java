package com.study.tdd.chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    //서비스를 사용하려면 매달 1만원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
    //2개월 이상 요금을 납부할 수 있다.
    //10만원을 납부하면 서비스를 1년 제공한다.현

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        assertEquals(expectedExpiryDate, cal.calculateExpiryDate(payData));
    }

    @Test
    void 만원_납부하면_한달_뒤_만료일() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 5, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 6, 1));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 8, 19))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 9, 19));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 12, 11))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 1, 11));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 2, 28));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 3, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 4, 30));
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2023, 8, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 9, 30));
    }
}
