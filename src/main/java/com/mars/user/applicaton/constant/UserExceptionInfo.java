package com.mars.user.applicaton.constant;

import com.mars.common.exception.vo.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserExceptionInfo {

  DUPLICATE_USER_ID("이미 존재하는 유저 아이디입니다.", HttpStatus.BAD_REQUEST),
  ACCOUNT_NOT_EXIST("해당 아이디를 가진 계정이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
  ID_PASSWORD_NOT_MATCH("아이디 혹은 비밀번호가 잘못 입력되었습니다.", HttpStatus.BAD_REQUEST);

  private final String message;
  private final HttpStatus status;

  UserExceptionInfo(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }

  public CustomException exception() {
    return new CustomException(this.message, this.status);
  }

  public CustomException exception(Throwable cause) {
    return new CustomException(this.message, this.status, cause);
  }
}
