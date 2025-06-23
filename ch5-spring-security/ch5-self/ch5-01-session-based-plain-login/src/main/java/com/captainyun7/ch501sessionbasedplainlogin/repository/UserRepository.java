package com.captainyun7.ch501sessionbasedplainlogin.repository;

import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // TODO
} 