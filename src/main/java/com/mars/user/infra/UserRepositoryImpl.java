package com.mars.user.infra;

import com.mars.user.domain.entity.User;
import com.mars.user.domain.repo.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserJpa userJpa;

  @Override
  public void save(User user) {
    userJpa.save(user);
  }

  @Override
  public Optional<User> findById(Long id) {
    return userJpa.findById(id);
  }

  @Override
  public Optional<User> findByAccountId(String accountId) {
    return userJpa.findByAccountId(accountId);
  }

  @Override
  public List<User> findAll() {
    return userJpa.findAll();
  }

  @Override
  public boolean existsByAccountId(String accountId) {
    return userJpa.existsByAccountId(accountId);
  }
}
