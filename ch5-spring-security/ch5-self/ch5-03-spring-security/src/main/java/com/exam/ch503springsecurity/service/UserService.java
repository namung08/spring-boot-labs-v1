package com.exam.ch503springsecurity.service;

import com.exam.ch503springsecurity.domain.User;
import com.exam.ch503springsecurity.dto.SignUpRequest;
import com.exam.ch503springsecurity.dto.UserResponse;
import com.exam.ch503springsecurity.repository.UserRepository;
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
        User newUser = signUpRequest.toDomain();

        return UserResponse.fromEntity(userRepository.save(newUser));
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
        return userRepository.findByUsername(username);
    }


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

     public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
