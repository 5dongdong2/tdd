package com.study.tdd.chap10;

import lombok.Builder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DoNotCouplingWIthTimeTest {

    @Test
    @DisplayName("실행 시점에 따라 테스트가 실패하는 경우")
    void testMemberBadCase() {
        MemberBadCase member = MemberBadCase.builder()
                .expiryDate(LocalDateTime.of(2024, 1, 1, 0, 0, 0, 0))
                .build();

        assertFalse(member.isExpired());
    }

    @Test
    @DisplayName("실행 시점과 관계없이 테스트가 수행되는 경우")
    void testMemberGoodCase() {
        MemberGoodCase member = MemberGoodCase.builder()
                .expiryDate(LocalDateTime.of(2024, 1, 1, 0, 0, 0, 0))
                .build();

        assertFalse(member.passedExpiryDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0)));
    }
}

@Builder
class MemberBadCase {
    private LocalDateTime expiryDate;

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDateTime.now());
    }
}

@Builder
class MemberGoodCase {
    private LocalDateTime expiryDate;

    public boolean passedExpiryDate(LocalDateTime dateTime) {
        return expiryDate.isBefore(dateTime);
    }
}