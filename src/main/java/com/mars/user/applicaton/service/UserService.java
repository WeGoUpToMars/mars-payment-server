package com.mars.user.applicaton.service;

import com.mars.user.domain.entity.User;
import com.mars.user.domain.repo.UserRepository;
import com.mars.user.presentation.dto.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  public UserJoinDto.Response join(UserJoinDto.Request request) {
    final var user = User.create(request.getName(), request.getEmail(), request.getProfile());
    userRepository.save(user);
    return User.toJoinResponse(user);
  }
}
