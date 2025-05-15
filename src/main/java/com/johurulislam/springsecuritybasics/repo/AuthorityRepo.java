package com.johurulislam.springsecuritybasics.repo;

import com.johurulislam.springsecuritybasics.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {
    Authority findByAuthorityName(String authorityName);
}
