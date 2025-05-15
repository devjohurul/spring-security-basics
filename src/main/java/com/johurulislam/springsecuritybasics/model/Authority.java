package com.johurulislam.springsecuritybasics.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "authority_table")
public class Authority {
    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    private long id;
    @Column(name = "authority_name")
    String authorityName;

}
