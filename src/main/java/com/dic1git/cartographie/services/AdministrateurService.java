package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.AdministrateurResponseDTO;
import com.dic1git.cartographie.entities.Administrateur;
import com.dic1git.cartographie.mappers.AdministrateurMapper;
import com.dic1git.cartographie.repositories.AdministrateurRepository;
import com.dic1git.cartographie.utils.EntityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdministrateurService {
    AdministrateurRepository administrateurRepository;
    AdministrateurMapper administrateurMapper;

    @Transactional
    public AdministrateurResponseDTO save(Administrateur administrateur) {
        administrateurRepository.save(administrateur);
        return administrateurMapper.toDTO(administrateur);
    }

    public AdministrateurResponseDTO findById(Long id) {
        Administrateur administrateur = EntityUtils.getEntityOrThrow(id, administrateurRepository, "Administrateur");
        return administrateurMapper.toDTO(administrateur);
    }

    public List<AdministrateurResponseDTO> findAll() {
        List<Administrateur> administrateurs = administrateurRepository.findAll();
        return administrateurs
                .stream()
                .map(administrateurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AdministrateurResponseDTO update(Long id, Administrateur administrateur) {
        Administrateur admin =  EntityUtils.getEntityOrThrow(id, administrateurRepository, "Administrateur");
        admin.setUsername(administrateur.getUsername());
        admin.setEmail(administrateur.getEmail());
        admin.setPassword(administrateur.getPassword());
        administrateurRepository.save(admin);
        return administrateurMapper.toDTO(admin);
    }

    @Transactional
    public void deleteAdministrateur(Long id) {
        EntityUtils.entityExistsOrThrow(id, administrateurRepository, "Administrateur");
        administrateurRepository.deleteById(id);
    }
}
