package com.dic1git.cartographie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RecommandationDTO {
    @NotBlank(message = "Nom de l'établissement est obligatoire")
    @JsonProperty(value = "nom_etablissement")
    private String nomEtablissement;

    @NotBlank(message = "Email de l'établissement est obligatoire")
    private String email;
    private String telephone;
}
