package com.example.jwtauthentication.service.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.jwtauthentication.model.Role;
import com.example.jwtauthentication.model.User;
import com.example.jwtauthentication.service.UserService;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final User EMPTY_USER = createEmptyUser();
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByLogin(username).orElse(EMPTY_USER);

        return withUsername(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole().getName())
                .build();
    }

    private static User createEmptyUser() {
        Role role = new Role();
        role.setName("USER");

        User emptyUser = new User();
        emptyUser.setRole(role);
        emptyUser.setLogin("empty");
        emptyUser.setPassword("empty");

        return emptyUser;
    }
}
