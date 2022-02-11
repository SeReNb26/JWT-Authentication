package com.example.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jwtauthentication.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(String name);
}
