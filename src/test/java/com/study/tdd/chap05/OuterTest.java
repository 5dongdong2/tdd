package com.study.tdd.chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class OuterTest {

    public OuterTest() {
        System.out.println("outer constructor: Test Class 생성");
    }

    @BeforeEach
    void outerBeforeEachSetUp() {
        System.out.println("outer @BeforeEach: before each test method");
    }

    @Test
    void outerATest() {
        System.out.println("outer A");
    }

    @Test
    void outerBTest() {
        System.out.println("outer B");
    }

    @AfterEach
    void outerAfterEachClear() {
        System.out.println("outer @AfterEach: after each test method");
    }

    @Nested
    class InnerTest {
        public InnerTest() {
            System.out.println("inner constructor: Test Class 생성");
        }

        @BeforeEach
        void InnerBeforeEachSetUp() {
            System.out.println("inner @BeforeEach: before each test method");
        }

        @Test
        void InnerATest() {
            System.out.println("inner A");
        }

        @Test
        void InnerBTest() {
            System.out.println("inner B");
        }

        @AfterEach
        void InnerAfterEachClear() {
            System.out.println("inner @AfterEach: after each test method");
        }
    }
}
