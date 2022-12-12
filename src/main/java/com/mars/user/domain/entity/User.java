package com.mars.user.domain.entity;

import com.mars.common.entity.BaseEntity;
import com.mars.user.presentation.dto.UserJoinDto;
import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String accountId;
  private String password;

  private String name;
  private String email;
  private String profile;

  private User(String name, String accountId, String password, String email, String profile) {
    validate(name, accountId, password);
    this.name = name;
    this.accountId = accountId;
    this.password = password;
    this.email = email;
    this.profile = profile;
  }

  public static User create(String name, String accountId, String password, String email, String profile) {
    return new User(name, accountId, password, email, profile);
  }

  public static UserJoinDto.Response toJoinResponse(User user) {
    return UserJoinDto.Response.builder()
                               .name(user.getName())
                               .accountId(user.getAccountId())
                               .email(user.getEmail())
                               .profile(user.getProfile())
                               .build();
  }

  private void validate(String name, String accountId, String password) {
    // TODO : 이메일 검증은 실제로 verification email을 전송하는 방식으로 진행한다.
    validateName(name);
    validateAccountId(accountId);
    validatePassword(password);
  }

  private void validateName(String name) {
    if (!Pattern.matches("^(?=.*[a-zA-Z가-힣])[a-zA-Z가-힣]{1,10}$", name)) {
      throw new IllegalArgumentException("한글, 영문 대,소문자로 구성된 1 ~ 10자로 구성되어야 합니다.");
    }
  }

  private void validateAccountId(String accountId) {
    if (!Pattern.matches("^(?=.*[a-z])[a-z\\d]{5,20}$", accountId)) {
      throw new IllegalArgumentException("영문 소문자와 숫자로 구성된 5자 ~ 20자여야 합니다. (영문자 필수 입력)");
    }
  }

  private void validatePassword(String password) {
    if (!Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[#?!@$%^&*-])[A-Za-z\\d#?!@$%^&*-]{8,16}$", password)) {
      throw new IllegalArgumentException("영문 대,소문자, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자로 구성되어야 합니다.");
    }
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}
