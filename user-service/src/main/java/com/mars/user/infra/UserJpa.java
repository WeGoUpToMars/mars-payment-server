package com.mars.user.infra;

import com.mars.user.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<User, Long> {

  Optional<User> findByAccountId(String accountId);

  boolean existsByAccountId(String accountId);
}
