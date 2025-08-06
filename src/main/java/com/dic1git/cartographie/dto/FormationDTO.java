package com.dic1git.cartographie.dto;

import com.dic1git.cartographie.entities.Accreditation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Setter
@Getter
public class FormationDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    private String description;
    @JsonIgnore
    private Set<Accreditation> accreditations;
}