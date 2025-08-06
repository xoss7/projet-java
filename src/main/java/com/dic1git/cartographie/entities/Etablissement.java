package com.dic1git.cartographie.entities;

import com.dic1git.cartographie.utils.StatutEtablissement;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "etablissement")
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String email;
    private String tel;
    private StatutEtablissement statut;
    @Column(name = "site_web")
    private String siteWeb;
    private String region;
    private String ville;
    private String localisation;

    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Formation> formations;

    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Infos> informations;
}
