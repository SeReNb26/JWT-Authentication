package com.example.jwtauthentication.config;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.example.jwtauthentication.model.Role;
import com.example.jwtauthentication.model.User;
import com.example.jwtauthentication.service.RoleService;
import com.example.jwtauthentication.service.UserService;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void inject() {
        Role admin = new Role();
        admin.setName("ADMIN");
        Role user = new Role();
        user.setName("USER");

        roleService.add(admin);
        roleService.add(user);

        User benito = new User();
        benito.setName("Benito");
        benito.setLogin("admin");
        benito.setPassword("admin");
        benito.setRole(admin);

        User viktor = new User();
        viktor.setName("Viktor");
        viktor.setLogin("user");
        viktor.setPassword("user");
        viktor.setRole(user);

        userService.add(benito);
        userService.add(viktor);
    }
}
