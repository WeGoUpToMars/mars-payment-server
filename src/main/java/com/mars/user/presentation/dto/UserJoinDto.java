package com.mars.user.presentation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJoinDto {

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Request {

    @NotBlank(message = "이름은 공백일 수 없습니다.")
    @Size(min = 1, max = 10, message = "이름은 1 ~ 10자로 구성되어야 합니다.")
    private String name;

    @Email
    private String email;

    private String profile;
  }

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Response {

    private String name;
    private String email;
    private String profile;
  }
}
