package com.captainyun7.ch504jwtbasedspringsecuritylogin.repository;

import com.captainyun7.ch504jwtbasedspringsecuritylogin.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(Long userId);
} 