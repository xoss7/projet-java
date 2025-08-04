package com.dic1git.cartographie.dto;

import com.dic1git.cartographie.utils.StatutEtablissement;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EtablissementDTO {

    @NotBlank(message = "Le nom est obligatoire.")
    @JsonProperty("nom_etablissement")
    private String nom;
    private String description;
    @NotBlank(message = "Le mail est obligatoire")
    private String email;
    @JsonProperty(value = "telephone")
    private String tel;
    private StatutEtablissement statut;
    @JsonProperty(value = "site_web")
    private String siteWeb;
    private String region;
    private String ville;
    private String localisation;
}