package com.example.jwtauthentication.service;

import java.util.List;
import java.util.Optional;
import com.example.jwtauthentication.model.Role;
import com.example.jwtauthentication.model.User;

public interface UserService {
    void add(User user);

    Optional<User> findByLogin(String login);

    List<User> getAll();

    User getById(Long userId);

    void update(Long id, String name, String login, String password, Role role);

    void deleteById(Long id);
}
