package com.mars.user.infra;

import com.mars.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<User, Long> {

}
