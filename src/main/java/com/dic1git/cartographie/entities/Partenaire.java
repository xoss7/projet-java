package com.dic1git.cartographie.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partenaire")
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "partenaires")
    private Set<Etablissement> etablissements;
}