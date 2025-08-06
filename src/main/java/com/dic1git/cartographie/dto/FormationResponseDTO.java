package com.dic1git.cartographie.dto;

import com.dic1git.cartographie.entities.Accreditation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Setter
@Getter
public class FormationResponseDTO {
    private Long id;
    private String nom;
    private String description;
    @JsonProperty(value = "etablissement_id")
    private Long etablissementId;
    @JsonIgnore
    private Set<Accreditation> accreditations;
}