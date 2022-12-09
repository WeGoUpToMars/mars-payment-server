package com.mars.user.presentation.dto;

import com.mars.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJoinDto {

  @Getter
  @AllArgsConstructor(staticName = "of")
  @NoArgsConstructor
  @Builder
  public static class Request {

    private String name;
    private String accountId;
    private String password;
    private String email;
    private String profile;
  }

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Response {

    private String name;
    private String accountId;
    private String email;
    private String profile;

    public static Response of(User user) {
      return new Response(user.getName(), user.getAccountId(), user.getEmail(), user.getProfile());
    }
  }
}
