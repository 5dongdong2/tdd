package com.study.tdd.chap07;

import com.study.tdd.chap07.checker.WeakPasswordChecker;
import com.study.tdd.chap07.core.UserRegister;
import com.study.tdd.chap07.exception.WeakPasswordException;
import com.study.tdd.chap07.notifier.EmailNotifier;
import com.study.tdd.chap07.repository.MemoryUserRepository;
import com.study.tdd.chap07.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

public class UserRegisterMockTest {

    private final WeakPasswordChecker mockWeakPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private final UserRepository memoryUserRepository = new MemoryUserRepository();
    private final EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);
    private UserRegister userRegister;

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockWeakPasswordChecker, memoryUserRepository, mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        given(mockWeakPasswordChecker.checkPasswordWeak("pw"))
                .willReturn(true);

        assertThrows(WeakPasswordException.class,
                () -> userRegister.register("id", "pw", "eamil"));
    }

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "eamil");

        then(mockWeakPasswordChecker)
                .should(only()).checkPasswordWeak(anyString());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should(only()).sendRegisterEmail(captor.capture());
        assertEquals("email@email.com", captor.getValue());
    }

    @DisplayName("가입 성공")
    @Test
    void registerSuccessfully() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        then(mockWeakPasswordChecker)
                .should(only()).checkPasswordWeak(stringArgumentCaptor.capture());
        assertEquals("pw", stringArgumentCaptor.getValue());
        then(mockEmailNotifier)
                .should(only()).sendRegisterEmail(stringArgumentCaptor.capture());
        assertEquals("email@email.com", stringArgumentCaptor.getValue());
    }
}
