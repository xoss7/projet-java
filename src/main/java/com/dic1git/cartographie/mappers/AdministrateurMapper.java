package com.dic1git.cartographie.mappers;

import com.dic1git.cartographie.dto.AdministrateurDTO;
import com.dic1git.cartographie.dto.AdministrateurResponseDTO;
import com.dic1git.cartographie.entities.Administrateur;
import org.springframework.stereotype.Component;

@Component
public class AdministrateurMapper {

    public AdministrateurResponseDTO toDTO(Administrateur administrateur) {
        return AdministrateurResponseDTO.builder()
                .id(administrateur.getId())
                .username(administrateur.getUsername())
                .email(administrateur.getEmail())
                .build();
    }

    public Administrateur toEntity(AdministrateurDTO administrateurDTO) {
        return Administrateur.builder()
                .username(administrateurDTO.getUsername())
                .email(administrateurDTO.getEmail())
                .password(administrateurDTO.getPassword())
                .build();
    }

}
