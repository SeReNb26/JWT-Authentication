package com.example.jwtauthentication.service;

import java.util.Optional;
import com.example.jwtauthentication.model.User;

public interface UserService {
    void add(User user);

    Optional<User> findByLogin(String login);
}
