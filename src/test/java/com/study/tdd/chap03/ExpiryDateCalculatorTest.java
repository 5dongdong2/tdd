package com.study.tdd.chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    //서비스를 사용하려면 매달 1만원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
    //2개월 이상 요금을 납부할 수 있다.
    //10만원을 납부하면 서비스를 1년 제공한다.

    @Test
    void 만원_납부하면_한달_뒤_만료일() {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();

        LocalDate billingDate = LocalDate.of(2023, 5, 1);
        int payAmount = 10_000;

        LocalDate expiryDate = cal.calculate(billingDate, payAmount);
        assertEquals(LocalDate.of(2023, 6, 1), expiryDate);

        LocalDate billingDate2 = LocalDate.of(2023, 8, 19);
        int payAmount2 = 10_000;

        LocalDate expiryDate2 = cal.calculate(billingDate2, payAmount2);
        assertEquals(LocalDate.of(2023, 9, 19), expiryDate2);
    }
}
