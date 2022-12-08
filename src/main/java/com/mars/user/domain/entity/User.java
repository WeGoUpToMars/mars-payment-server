package com.mars.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mars.common.entity.BaseEntity;
import com.mars.user.presentation.dto.UserJoinDto;
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

  private String name;
  private String email;
  private String profile;

  @JsonCreator
  private User(@JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("profile") String profile) {
    this.name = name;
    this.email = email;
    this.profile = profile;
  }

  public static User create(String name, String email, String profile) {
    return new User(name, email, profile);
  }

  public static UserJoinDto.Response toJoinResponse(User user) {
    return UserJoinDto.Response.builder()
                               .name(user.getName())
                               .email(user.getEmail())
                               .profile(user.getProfile())
                               .build();
  }
}
