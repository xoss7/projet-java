package com.dic1git.cartographie.dto;

import com.dic1git.cartographie.utils.StatutEtablissement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EtablissementResponseDTO {
    private Long id;
    @JsonProperty("nom_etablissement")
    private String nom;
    private String description;
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