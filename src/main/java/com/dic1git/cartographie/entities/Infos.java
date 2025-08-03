package com.dic1git.cartographie.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Infos")
public class Infos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String contenu;

    @Builder.Default
    @CreatedDate
    @Column(name = "publish_date")
    private LocalDateTime publishDate =LocalDateTime.now(ZoneOffset.UTC);

    @ManyToOne
    @JoinColumn(name = "etablissement_id")
    @JsonBackReference
    private Etablissement etablissement;
}