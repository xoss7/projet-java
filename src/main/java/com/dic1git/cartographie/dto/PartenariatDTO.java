package com.dic1git.cartographie.dto;

import com.dic1git.cartographie.utils.TypePartenariat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PartenariatDTO {
    @NotBlank(message = "Le id de l'etablissement est requis")
    @JsonProperty(value = "etablissement_id")
    private Long etablissementId;

    @NotBlank(message = "Le id du partenaire est requis")
    @JsonProperty(value = "partenaire_id")
    private Long partenaireId;

    private TypePartenariat type;

    @JsonProperty(value = "date_debut")
    private LocalDate dateDebut;
}
