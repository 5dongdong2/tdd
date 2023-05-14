package com.study.tdd.chap05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class TimeoutTest {

    @Test
    @Timeout(2)
    void timeoutTest() throws InterruptedException {
        Thread.sleep(1_000);
    }
}
