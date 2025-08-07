package com.dic1git.cartographie.mappers;

import com.dic1git.cartographie.dto.PartenaireDTO;
import com.dic1git.cartographie.dto.PartenaireResponseDTO;
import com.dic1git.cartographie.entities.Partenaire;
import org.springframework.stereotype.Component;

@Component
public class PartenaireMapper {

    public Partenaire toEntity(PartenaireDTO partenaireDTO) {
        return Partenaire.builder()
                .nom(partenaireDTO.getNom())
                .email(partenaireDTO.getEmail())
                .secteur(partenaireDTO.getSecteur())
                .build();
    }

    public PartenaireResponseDTO toDTO(Partenaire partenaire) {
        return PartenaireResponseDTO.builder()
                .id(partenaire.getId())
                .nom(partenaire.getNom())
                .email(partenaire.getEmail())
                .secteur(partenaire.getSecteur())
                .build();
    }

}
