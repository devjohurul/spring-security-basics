package com.johurulislam.springsecuritybasics.repo;

import com.johurulislam.springsecuritybasics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
}
