package com.mars.user.domain.entity;

import com.mars.core.entity.BaseEntity;
import com.mars.user.domain.constant.UserExceptionInfo;
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

  private static final String NAME_VALIDATION_REGEX = "^(?=.*[a-zA-Z가-힣])[a-zA-Z가-힣]{1,10}$";
  private static final String ACCOUNT_ID_VALIDATION_REGEX = "^(?=.*[a-z])[a-z\\d]{5,20}$";
  private static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[#?!@$%^&*-])[A-Za-z\\d#?!@$%^&*-]{8,16}$";

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
    if (!Pattern.matches(NAME_VALIDATION_REGEX, name)) {
      throw UserExceptionInfo.NOT_VALID_USER_NAME.exception();
    }
  }

  private void validateAccountId(String accountId) {
    if (!Pattern.matches(ACCOUNT_ID_VALIDATION_REGEX, accountId)) {
      throw UserExceptionInfo.NOT_VALID_ACCOUNT_ID.exception();
    }
  }

  private void validatePassword(String password) {
    if (!Pattern.matches(PASSWORD_VALIDATION_REGEX, password)) {
      throw UserExceptionInfo.NOT_VALID_PASSWORD.exception();
    }
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}
