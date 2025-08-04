package com.dic1git.cartographie.mappers;

import com.dic1git.cartographie.dto.RecommandationDTO;
import com.dic1git.cartographie.dto.RecommandationResponseDTO;
import com.dic1git.cartographie.entities.Recommandation;
import org.springframework.stereotype.Component;

@Component
public class RecommandationMapper {
    public Recommandation toEntity(RecommandationDTO recommandationDTO) {
        return Recommandation.builder()
                .nomEtablissement(recommandationDTO.getNomEtablissement())
                .email(recommandationDTO.getEmail())
                .telephone(recommandationDTO.getTelephone())
                .build();
    }

    public RecommandationResponseDTO toDTO(Recommandation recommandation) {
        return RecommandationResponseDTO.builder()
                .id(recommandation.getId())
                .nomEtablissement(recommandation.getNomEtablissement())
                .email(recommandation.getEmail())
                .telephone(recommandation.getTelephone())
                .build();
    }
}
