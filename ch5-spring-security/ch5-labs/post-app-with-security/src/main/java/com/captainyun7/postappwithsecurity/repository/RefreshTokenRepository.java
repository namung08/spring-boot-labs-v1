package com.captainyun7.postappwithsecurity.repository;

import com.captainyun7.postappwithsecurity.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
 Optional<RefreshToken> findByUser_Id(Long id);
}
