package com.johurulislam.springsecuritybasics.service;

import com.johurulislam.springsecuritybasics.model.Authority;
import com.johurulislam.springsecuritybasics.model.User;
import com.johurulislam.springsecuritybasics.repo.AuthorityRepo;
import com.johurulislam.springsecuritybasics.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final AuthorityRepo authorityRepo;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepo userRepo, AuthorityRepo authorityRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authorityRepo = authorityRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public User findByUsername(String username) {
        return userRepo.findByUsernameIgnoreCase(username);
    }
    public User saveUser(User user) {
        Authority userAuthority = authorityRepo.findByAuthorityName("ROLE_USER");
        user.setAuthorities(Set.of(userAuthority));
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public User saveAdmin(User user) {
        Authority userAuthority = authorityRepo.findByAuthorityName("ROLE_ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Set.of(userAuthority));
        return userRepo.save(user);
    }
}
