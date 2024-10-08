package com.riwi.PalletTrack.application.services;

import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.UserEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.mapper.UserMapper;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                List.of(() -> userEntity.getRole().name())
        );
    }
}
