package com.study.tdd.chap07;

import com.study.tdd.chap07.checker.StubWeakPasswordChecker;
import com.study.tdd.chap07.core.UserRegister;
import com.study.tdd.chap07.exception.DuplicatedIdException;
import com.study.tdd.chap07.exception.WeakPasswordException;
import com.study.tdd.chap07.model.User;
import com.study.tdd.chap07.notifier.SpyEmailNotifier;
import com.study.tdd.chap07.repository.MemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {

    //fake repository
    private final MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    //stub checker
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    //spy email notifier
    private final SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();
    private UserRegister userRegister;

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository, spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true); //암호가 약하다고 응답하도록 설정

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("동일한 ID를 가진 회원이 존재할 경우 가입 실패")
    @Test
    void sameIdExists() {
        memoryUserRepository.save(new User("id", "pw", "eamil@email.com"));

        assertThrows(DuplicatedIdException.class, () -> {
            userRegister.register("id", "pw", "eamil@email.com");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void registerSuccess() {
        userRegister.register("id", "pw", "email");

        User user = memoryUserRepository.findById("id");
        assertEquals("id", user.getId());
        assertEquals("email", user.getEmail());
    }

    @DisplayName("가입하면 메일을 전송")
    @Test
    void whenRegisterThenSendEmail() {
        userRegister.register("id", "pw", "email@email.com");

        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }
}
