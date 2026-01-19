package com.sarthak.twitterclone.auth.service;

import com.sarthak.twitterclone.common.exception.InvalidCredentialsException;
import com.sarthak.twitterclone.user.User;
import com.sarthak.twitterclone.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String email, String password) {
        User user = userRepository
                .findByEmail(email.toLowerCase())
                .orElseThrow(() -> new InvalidCredentialsException());

        if(!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new InvalidCredentialsException();
        }

        if(!user.isActive()) {
            throw new InvalidCredentialsException();
        }

        return user;
    }
}
