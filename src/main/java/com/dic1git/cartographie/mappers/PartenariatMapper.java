package com.dic1git.cartographie.mappers;

import com.dic1git.cartographie.dto.PartenariatDTO;
import com.dic1git.cartographie.dto.PartenariatResponseDTO;
import com.dic1git.cartographie.entities.Partenariat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PartenariatMapper {

    EtablissementMapper etablissementMapper;
    PartenaireMapper partenaireMapper;

    public Partenariat toEntity(PartenariatDTO partenariatDTO) {
        return Partenariat.builder()
                .type(partenariatDTO.getType())
                .dateDebut(partenariatDTO.getDateDebut())
                .build();
    }

    public PartenariatResponseDTO toDTO(Partenariat partenariat) {
        return PartenariatResponseDTO.builder()
                .id(partenariat.getId())
                .etablissement(etablissementMapper.toDTO(partenariat.getEtablissement()))
                .partenaire(partenaireMapper.toDTO(partenariat.getPartenaire()))
                .type(partenariat.getType())
                .dateDebut(partenariat.getDateDebut())
                .build();
    }
}
