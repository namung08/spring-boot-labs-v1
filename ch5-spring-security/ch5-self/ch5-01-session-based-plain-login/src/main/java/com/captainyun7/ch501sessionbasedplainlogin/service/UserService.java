package com.captainyun7.ch501sessionbasedplainlogin.service;

import com.captainyun7.ch501sessionbasedplainlogin.dto.SignUpRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.UserResponse;
import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import com.captainyun7.ch501sessionbasedplainlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public UserResponse createUser(SignUpRequest signUpRequest) {
        // TODO
        return null;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + id));
    }

    public Optional<User> getUserByUsername(String username) {
        // TODO
        return Optional.empty();
    }


    public boolean existsByUsername(String username) {
        // TODO
        return true;
    }

     public boolean existsByEmail(String email) {
         // TODO
        return true;
    }
} 