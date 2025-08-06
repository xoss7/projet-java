package com.dic1git.cartographie.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InfosDTO {
    @NotBlank
    private String titre;
    @NotBlank
    private String contenu;
}
