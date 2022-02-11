package com.example.jwtauthentication.repository;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.jwtauthentication.model.Role;
import com.example.jwtauthentication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = ?2, u.login = ?3, u.password = ?4, u.role = ?5 WHERE u.id = ?1")
    void update(Long id, String name, String login, String password, Role role);
}
