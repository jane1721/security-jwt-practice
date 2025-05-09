package com.jane.securityjwtpractice.user.intrastructure;

import com.jane.securityjwtpractice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
