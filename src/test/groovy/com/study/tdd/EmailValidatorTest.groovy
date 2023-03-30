package com.study.tdd

import spock.lang.Specification

class EmailValidatorTest extends Specification {

    def target = new EmailValidator()

    def "testEmailValidator - success"() {
        given:
        def email = CASE

        expect:
        target.validate(email)

        where:
        DESC | CASE
        "1"  | "test@example.com"
        "2"  | "test123@example.com"
        "3"  | "test_123@example.com"
        "4"  | "test-123@example.com"
    }

    def "testEmailValidator - fail"() {
        given:
        def email = CASE

        expect:
        target.validate(email) == false

        where:
        DESC | CASE
        "1"  | "test@example"
        "2"  | "test@.com"
        "3"  | "test@com."
        "4"  | "test@com.."
    }
}
