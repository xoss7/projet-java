package com.dic1git.cartographie.mappers;

import com.dic1git.cartographie.dto.FormationDTO;
import com.dic1git.cartographie.dto.FormationResponseDTO;
import com.dic1git.cartographie.entities.Formation;
import org.springframework.stereotype.Component;

@Component
public class FormationMapper {

    public Formation toEntity(FormationDTO formationDTO) {
        return Formation.builder()
                .nom(formationDTO.getNom())
                .description(formationDTO.getDescription())
                .accreditations(formationDTO.getAccreditations())
                .build();
    }

    public FormationResponseDTO toDTO(Formation formation) {
        return FormationResponseDTO.builder()
                .id(formation.getId())
                .nom(formation.getNom())
                .description(formation.getDescription())
                .etablissementId(formation.getEtablissement().getId())
                .accreditations(formation.getAccreditations())
                .build();
    }

}
