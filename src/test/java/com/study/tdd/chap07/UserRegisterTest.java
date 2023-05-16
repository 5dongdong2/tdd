package com.study.tdd.chap07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {

    private final MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private UserRegister userRegister;

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true); //암호가 약하다고 응답하도록 설정

        Assertions.assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("동일한 ID를 가진 회원이 존재할 경우 가입 실패")
    @Test
    void sameIdExists() {
        memoryUserRepository.save(new User("id", "pw", "eamil@email.com"));

        Assertions.assertThrows(DuplicatedIdException.class, () -> {
            userRegister.register("id", "pw", "eamil@email.com");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void registerSuccess() {
        userRegister.register("id", "pw", "email");

        User user = memoryUserRepository.findById("id");
        Assertions.assertEquals("id", user.getId());
        Assertions.assertEquals("email", user.getEmail());
    }
}
