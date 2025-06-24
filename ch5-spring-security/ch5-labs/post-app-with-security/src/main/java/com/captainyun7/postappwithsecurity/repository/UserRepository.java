package com.captainyun7.postappwithsecurity.repository;

import com.captainyun7.postappwithsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
 boolean existsByUsername(String username);
}
