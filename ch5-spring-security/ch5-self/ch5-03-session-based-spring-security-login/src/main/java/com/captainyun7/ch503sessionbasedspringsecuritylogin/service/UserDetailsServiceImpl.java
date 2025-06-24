package com.captainyun7.ch503sessionbasedspringsecuritylogin.service;

import com.captainyun7.ch503sessionbasedspringsecuritylogin.domain.User;
import com.captainyun7.ch503sessionbasedspringsecuritylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: [6] 사용자 이름(username)을 사용하여 데이터베이스에서 사용자 정보를 조회합니다.
        User user = userRepository.findByUsername(username)
            // TODO: [7] 조회된 사용자 정보가 없으면 UsernameNotFoundException을 발생시킵니다.
            .orElseThrow(() -> new UsernameNotFoundException(username));
        // TODO: [8] 조회된 사용자 정보를 기반으로 UserDetails 객체를 생성하여 반환합니다.
        //      - UserDetails는 Spring Security에서 사용자의 정보를 나타내는 인터페이스입니다.
        //      - org.springframework.security.core.userdetails.User 클래스를 사용하여 UserDetails 객체를 생성할 수 있습니다.
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
//        return new UserDetails() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                Collection<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add((GrantedAuthority) () -> "ROLE_" + user.getRole());
//                return authorities;
//            }
//
//            @Override
//            public String getPassword() {
//                return user.getPassword();
//            }
//
//            @Override
//            public String getUsername() {
//                return user.getUsername();
//            }
//        };
    }
}
