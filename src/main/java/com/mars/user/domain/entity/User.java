package com.mars.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

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
}
