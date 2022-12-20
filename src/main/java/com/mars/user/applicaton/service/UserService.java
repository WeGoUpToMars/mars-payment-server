package com.mars.user.applicaton.service;

import com.mars.user.applicaton.constant.UserExceptionInfo;
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
    if (userRepository.existsByAccountId(request.getAccountId())) {
      throw UserExceptionInfo.DUPLICATE_USER_ID.exception();
    }
  }

  public String login(UserLoginDto.Request request) {
    final var user = userRepository.findByAccountId(request.getAccountId())
                                   .orElseThrow(UserExceptionInfo.ACCOUNT_NOT_EXIST::exception);
    matchPassword(user, request);
    return "OK";
  }

  private void matchPassword(final User user, final Request request) {
    if (!user.checkPassword(request.getPassword())) {
      throw UserExceptionInfo.ID_PASSWORD_NOT_MATCH.exception();
    }
  }

  public List<Response> findAll() {
    return userRepository.findAll().stream()
                         .map(UserJoinDto.Response::of)
                         .collect(Collectors.toList());
  }
}
