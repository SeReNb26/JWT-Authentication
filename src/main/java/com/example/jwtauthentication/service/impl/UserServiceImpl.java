package com.example.jwtauthentication.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.jwtauthentication.model.Role;
import com.example.jwtauthentication.model.User;
import com.example.jwtauthentication.repository.UserRepository;
import com.example.jwtauthentication.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long userId) {
        return userRepository.getById(userId);
    }

    @Override
    public void update(Long id, String name, String login, String password, Role role) {
        userRepository.update(id, name, login, password, role);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
