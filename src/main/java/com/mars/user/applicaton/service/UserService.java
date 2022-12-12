package com.mars.user.applicaton.service;

import com.mars.user.domain.entity.User;
import com.mars.user.domain.repo.UserRepository;
import com.mars.user.presentation.dto.UserJoinDto;
import com.mars.user.presentation.dto.UserJoinDto.Response;
import com.mars.user.presentation.dto.UserLoginDto;
import com.mars.user.presentation.dto.UserLoginDto.Request;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public UserJoinDto.Response join(UserJoinDto.Request request) {
    checkDuplication(request);
    final var user = User.create(request.getName(), request.getAccountId(), request.getPassword(), request.getEmail(), request.getProfile());
    userRepository.save(user);
    return User.toJoinResponse(user);
  }

  private void checkDuplication(final UserJoinDto.Request request) {
    if (!userRepository.existsByAccountId(request.getAccountId())) {
      throw new IllegalArgumentException("이미 존재하는 유저 아이디입니다.");
    }
  }

  public String login(UserLoginDto.Request request) {
    final var user = userRepository.findByAccountId(request.getAccountId()).orElse(null);
    checkPresence(user);
    matchPassword(user, request);
    return "OK";
  }

  private void matchPassword(final User user, final Request request) {
    if (!user.getPassword().equals(request.getPassword())) {
      throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못 입력되었습니다.");
    }
  }

  private void checkPresence(final User user) {
    if (user == null) {
      throw new IllegalArgumentException("해당 아이디를 가진 계정이 존재하지 않습니다.");
    }
  }

  public List<Response> findAll() {
    return userRepository.findAll().stream()
                         .map(UserJoinDto.Response::of)
                         .collect(Collectors.toList());
  }
}
