package com.dic1git.cartographie.dto;

import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.entities.Partenaire;
import com.dic1git.cartographie.utils.TypePartenariat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class PartenariatResponseDTO {
    private Long id;
    private EtablissementResponseDTO etablissement;
    private PartenaireResponseDTO partenaire;
    private TypePartenariat type;
    @JsonProperty(value = "date_debut")
    private LocalDate dateDebut;
}