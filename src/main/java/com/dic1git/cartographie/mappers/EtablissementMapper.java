package com.dic1git.cartographie.mappers;

import com.dic1git.cartographie.dto.EtablissementDTO;
import com.dic1git.cartographie.dto.EtablissementResponseDTO;
import com.dic1git.cartographie.entities.Etablissement;
import org.springframework.stereotype.Component;

@Component
public class EtablissementMapper {

    public Etablissement toEntity(EtablissementDTO etablissementDTO) {
        return Etablissement.builder()
                .nom(etablissementDTO.getNom())
                .description(etablissementDTO.getDescription())
                .email(etablissementDTO.getEmail())
                .tel(etablissementDTO.getTel())
                .statut(etablissementDTO.getStatut())
                .siteWeb(etablissementDTO.getSiteWeb())
                .region(etablissementDTO.getRegion())
                .ville(etablissementDTO.getVille())
                .localisation(etablissementDTO.getLocalisation())
                .build();
    }

    public EtablissementResponseDTO toDTO(Etablissement etablissement) {
        return EtablissementResponseDTO.builder()
                .id(etablissement.getId())
                .nom(etablissement.getNom())
                .description(etablissement.getDescription())
                .email(etablissement.getEmail())
                .tel(etablissement.getTel())
                .statut(etablissement.getStatut())
                .siteWeb(etablissement.getSiteWeb())
                .region(etablissement.getRegion())
                .ville(etablissement.getVille())
                .localisation(etablissement.getLocalisation())
                .build();
    }
}
