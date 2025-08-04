package com.dic1git.cartographie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RecommandationResponseDTO {
    private Long id;
    @JsonProperty(value = "nom_etablissement")
    private String nomEtablissement;
    private String email;
    private String telephone;
}
