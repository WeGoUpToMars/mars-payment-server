package com.mars.user.applicaton.service;

import com.mars.test.MarsTest;
import com.mars.user.presentation.dto.UserJoinDto;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MarsTest
class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  void join() {
    final var user1 = UserJoinDto.Request.builder()
                                         .name("yong")
                                         .accountId("dfdf")
                                         .password("1234")
                                         .email("dfdf@gmail.com")
                                         .profile("/user.jpg")
                                         .build();
    final var user2 = UserJoinDto.Request.builder()
                                         .name("joon")
                                         .accountId("fefe")
                                         .password("1234")
                                         .email("fefe@gmail.com")
                                         .profile(null)
                                         .build();

    userService.join(user1);
    userService.join(user2);

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
}
