package com.johurulislam.springsecuritybasics.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;
    @Column(name = "first_name")
    private String fristName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;
}

