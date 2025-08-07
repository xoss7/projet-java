package com.dic1git.cartographie.entities;

import com.dic1git.cartographie.utils.TypePartenariat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partenariat")
public class Partenariat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etablissement_id", nullable = false)
    private Etablissement etablissement;

    @ManyToOne
    @JoinColumn(name = "partenaire_id", nullable = false)
    private Partenaire partenaire;

    private LocalDate dateDebut;
    private TypePartenariat type;
}