package com.study.tdd.chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LifeCycleTest {
    public LifeCycleTest() {
        System.out.println("constructor: Test Class 생성");
    }

    @BeforeEach
    void beforeEachSetUp() {
        System.out.println("@BeforeEach: before each test method");
    }

    @Test
    void aTest() {
        System.out.println("A");
    }

    @Test
    void bTest() {
        System.out.println("b");
    }

    @AfterEach
    void afterEachClear() {
        System.out.println("@AfterEach: after each test method");
    }
}
