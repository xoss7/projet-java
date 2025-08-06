package com.dic1git.cartographie.dto;

import com.dic1git.cartographie.entities.Etablissement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class PartenaireResponseDTO {
    private Long id;
    private String nom;
    private String email;
    private String secteur;
}