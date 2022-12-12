package com.mars.user.presentation.dto;

import com.mars.user.domain.entity.User;
import javax.validation.constraints.Pattern;
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

    @Pattern(regexp = "^(?=.*[a-zA-Z가-힣])[a-zA-Z가-힣]{1,10}$")
    private String name;
    @Pattern(regexp = "^(?=.*[a-z])[a-z\\d]{5,20}$")
    private String accountId;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[#?!@$%^&*-])[A-Za-z\\d#?!@$%^&*-]{8,16}$")
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
