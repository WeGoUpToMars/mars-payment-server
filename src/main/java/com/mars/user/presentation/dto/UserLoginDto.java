package com.mars.user.presentation.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginDto {

  @Getter
  @AllArgsConstructor(staticName = "of")
  @NoArgsConstructor
  @Builder
  public static class Request {

    private String accountId;
    private String password;
  }
}
