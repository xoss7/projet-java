package com.dic1git.cartographie.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PartenaireDTO {
    @NotBlank
    private String nom;
    @NotBlank
    private String email;
    private String secteur;
}