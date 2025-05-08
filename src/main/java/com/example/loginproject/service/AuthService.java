package com.example.loginproject.service;

import com.example.loginproject.model.User;
import com.example.loginproject.repository.UserRepository;
import com.example.loginproject.Util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
//        String hashedPassword = SHA256Util.encode(password); // 입력비번 암호화
        User user = userRepository.findByUsername(username);
//        return user != null && user.getPassword().equals(hashedPassword);
        return user != null && SHA256Util.matches(password, user.getPassword());
    }

    public void register(String username, String password) {
        userRepository.save(new User(username, SHA256Util.encode(password)));
    }
}
