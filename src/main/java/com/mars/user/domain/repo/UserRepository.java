package com.mars.user.domain.repo;

import com.mars.user.domain.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> findById(Long id);

  List<User> findAll();
}
