package com.opentech.users.application.model;

import com.opentech.users.application.enums.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(length = 50, nullable = false)
    private String nom;

    @Column(length = 100, nullable = false)
    private String prenom;

    @Column(unique = true, length = 60)
    private String username;


    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String telephone;

    @Column(columnDefinition = "text")
    private String adresse;
}
