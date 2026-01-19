package com.sarthak.twitterclone.user;

import com.sarthak.twitterclone.common.exception.EmailAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(String email, String rawPassword) {
        String normalizedEmail = email.toLowerCase();

        if(userRepository.existsByEmail(normalizedEmail)){
            throw new EmailAlreadyExistsException(email);
        }

        User user = new User();
        user.setEmail(normalizedEmail);
        user.setPasswordHash(hashPassword(rawPassword));
        user.setRole(Role.USER);
        user.setActive(true);

        return userRepository.save(user);
    }
    private String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
