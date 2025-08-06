package com.dic1git.cartographie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class InfosResponseDTO {
    private Long id;
    @JsonProperty(value = "etablissement_id")
    private Long etablissementId;
    private String titre;
    private String contenu;
    @JsonProperty(value = "publish_date")
    private LocalDateTime publishDate;
}