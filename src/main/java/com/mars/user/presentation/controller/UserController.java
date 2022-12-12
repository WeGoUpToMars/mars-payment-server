package com.mars.user.presentation.controller;

import com.mars.user.applicaton.service.UserService;
import com.mars.user.presentation.dto.UserJoinDto;
import com.mars.user.presentation.dto.UserJoinDto.Response;
import com.mars.user.presentation.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mars/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(value = "/join", produces = "application/json")
  public ResponseEntity<Response> join(UserJoinDto.Request request) {
    return new ResponseEntity<>(userService.join(request), null, HttpStatus.OK);
  }

  @PostMapping(value = "/login", produces = "application/json")
  public ResponseEntity<String> login(UserLoginDto.Request request) {
    return new ResponseEntity<>(userService.login(request), null, HttpStatus.OK);
  }
}
