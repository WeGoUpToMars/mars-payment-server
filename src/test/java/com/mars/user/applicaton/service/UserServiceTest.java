package com.mars.user.applicaton.service;

import com.mars.test.MarsTest;
import com.mars.user.presentation.dto.UserJoinDto;
import com.mars.user.presentation.dto.UserLoginDto;
import com.mars.user.presentation.dto.UserLoginDto.Request;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MarsTest
class UserServiceTest {

  @Autowired
  private UserService userService;

  private static UserJoinDto.Request userJoinRequest1;
  private static UserJoinDto.Request userJoinRequest2;

  @BeforeEach
  void beforeEach() {
    userJoinRequest1 = UserJoinDto.Request.builder()
                                          .name("yong")
                                          .accountId("dfdfdf")
                                          .password("qwe!@#123")
                                          .email("dfdf@gmail.com")
                                          .profile("/user.jpg")
                                          .build();
    userJoinRequest2 = UserJoinDto.Request.builder()
                                          .name("joon")
                                          .accountId("fefefe")
                                          .password("wer@#$234")
                                          .email("fefe@gmail.com")
                                          .profile(null)
                                          .build();
  }

  @Test
  @DisplayName("회원 가입 로직 테스트")
  void join() {
    userService.join(userJoinRequest1);
    userService.join(userJoinRequest2);

    final var users = userService.findAll();

    assertAll(
            () -> assertEquals(2, users.size()),
            () -> assertEquals("dfdf@gmail.com", users.stream()
                                                      .filter(e -> e.getName().equals("yong"))
                                                      .findAny()
                                                      .orElseThrow(NoSuchElementException::new).getEmail()),
            () -> assertNull(users.stream()
                                  .filter(e -> e.getEmail().equals("fefe@gmail.com"))
                                  .findAny()
                                  .orElseThrow(NoSuchElementException::new).getProfile())
    );

  }

  @Test
  @DisplayName("로그인 로직 테스트")
  void login() {
    userService.join(userJoinRequest1);

    assertDoesNotThrow(() -> userService.login(UserLoginDto.Request.builder()
                                                                   .accountId(userJoinRequest1.getAccountId())
                                                                   .password(userJoinRequest1.getPassword())
                                                                   .build()));

    final var wrongRequest1 = Request.builder()
                                     .accountId("wrongId")
                                     .password(userJoinRequest1.getPassword())
                                     .build();
    final var wrongRequest2 = Request.builder()
                                     .accountId(userJoinRequest1.getAccountId())
                                     .password("wrongPassword")
                                     .build();
    assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> userService.login(wrongRequest1)),
            () -> assertThrows(IllegalArgumentException.class, () -> userService.login(wrongRequest2))
    );

  }
}
