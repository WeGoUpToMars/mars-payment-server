package com.mars.user.domain.entity;

import com.mars.common.test.MarsTest;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MarsTest
class UserTest {

  @Test
  @DisplayName("이름 필드 유효성 검사 테스트")
  void validateName() throws NoSuchMethodException {
    final var user = new User();
    final var nameValidation = User.class.getDeclaredMethod("validateName", String.class);
    nameValidation.setAccessible(true);

    assertAll(
            () -> assertDoesNotThrow(() -> nameValidation.invoke(user, "a")),
            () -> assertDoesNotThrow(() -> nameValidation.invoke(user, "D")),
            () -> assertDoesNotThrow(() -> nameValidation.invoke(user, "한")),
            () -> assertDoesNotThrow(() -> nameValidation.invoke(user, "한afkeAvmd두")),
            () -> assertDoesNotThrow(() -> nameValidation.invoke(user, "한길동만")),
            () -> assertDoesNotThrow(() -> nameValidation.invoke(user, "dfEAFfad"))
    );

    assertAll(
            () -> assertThrows(InvocationTargetException.class,
                               () -> nameValidation.invoke(user, "")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> nameValidation.invoke(user, " ")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> nameValidation.invoke(user, "-")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> nameValidation.invoke(user, "1")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> nameValidation.invoke(user, "ab한23")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> nameValidation.invoke(user, "df아니아르dsdsdsDFV")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> nameValidation.invoke(user, "fasen_123")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> nameValidation.invoke(user, "jke 231"))
    );
  }

  @Test
  @DisplayName("계정 아이디 필드 유효성 검사 테스트")
  void validateAccountId() throws NoSuchMethodException {
    final var user = new User();
    final var accountIdValidation = User.class.getDeclaredMethod("validateAccountId", String.class);
    accountIdValidation.setAccessible(true);

    assertAll(
            () -> assertDoesNotThrow(() -> accountIdValidation.invoke(user, "asdasd")),
            () -> assertDoesNotThrow(() -> accountIdValidation.invoke(user, "asdasd123")),
            () -> assertDoesNotThrow(() -> accountIdValidation.invoke(user, "asda12")),
            () -> assertDoesNotThrow(() -> accountIdValidation.invoke(user, "qwertqwertqwertqwert"))
    );

    assertAll(
            () -> assertThrows(InvocationTargetException.class,
                               () -> accountIdValidation.invoke(user, "")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> accountIdValidation.invoke(user, " ")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> accountIdValidation.invoke(user, "-")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> accountIdValidation.invoke(user, "112345")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> accountIdValidation.invoke(user, "asd1")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> accountIdValidation.invoke(user, "fsdafsdffsafdsfsdfsdfsdfsafsdf2312")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> accountIdValidation.invoke(user, "fasen_123")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> accountIdValidation.invoke(user, "jke 231"))
    );
  }

  @Test
  @DisplayName("비밀번호 필드 유효성 검사 테스트")
  void validatePassword() throws NoSuchMethodException {
    final var user = new User();
    final var passwordValidation = User.class.getDeclaredMethod("validatePassword", String.class);
    passwordValidation.setAccessible(true);

    assertAll(
            () -> assertDoesNotThrow(() -> passwordValidation.invoke(user, "Asdf123!@#$")),
            () -> assertDoesNotThrow(() -> passwordValidation.invoke(user, "asd1!@#A")),
            () -> assertDoesNotThrow(() -> passwordValidation.invoke(user, "ASDFqwer1234!@#$")),
            () -> assertDoesNotThrow(() -> passwordValidation.invoke(user, "123!@#QWE"))
    );

    assertAll(
            () -> assertThrows(InvocationTargetException.class,
                               () -> passwordValidation.invoke(user, "")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> passwordValidation.invoke(user, " ")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> passwordValidation.invoke(user, "-")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> passwordValidation.invoke(user, "112345")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> passwordValidation.invoke(user, "asd1")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> passwordValidation.invoke(user, "fsdafsdffsafdsfsdfsdfsdfsafsdf2312")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> passwordValidation.invoke(user, "fasen_123")),
            () -> assertThrows(InvocationTargetException.class,
                               () -> passwordValidation.invoke(user, "jke 231"))
    );
  }
}
