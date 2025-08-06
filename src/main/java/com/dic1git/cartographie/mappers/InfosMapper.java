package com.dic1git.cartographie.mappers;

import com.dic1git.cartographie.dto.InfosDTO;
import com.dic1git.cartographie.dto.InfosResponseDTO;
import com.dic1git.cartographie.entities.Infos;
import org.springframework.stereotype.Component;

@Component
public class InfosMapper {

    public Infos toEntity(final InfosDTO dto) {
        return Infos.builder()
                .titre(dto.getTitre())
                .contenu(dto.getContenu())
                .build();
    }

    public InfosResponseDTO toDTO(final Infos entity) {
        return InfosResponseDTO.builder()
                .id(entity.getId())
                .titre(entity.getTitre())
                .contenu(entity.getContenu())
                .etablissementId(entity.getEtablissement().getId())
                .publishDate(entity.getPublishDate())
                .build();
    }

}
