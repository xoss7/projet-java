package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.AdministrateurDTO;
import com.dic1git.cartographie.dto.AdministrateurResponseDTO;
import com.dic1git.cartographie.entities.Administrateur;
import com.dic1git.cartographie.exceptions.ItemNotFoundException;
import com.dic1git.cartographie.mappers.AdministrateurMapper;
import com.dic1git.cartographie.repositories.AdministrateurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AdministrateurService {
    AdministrateurRepository administrateurRepository;
    AdministrateurMapper administrateurMapper;

    public AdministrateurResponseDTO save(Administrateur administrateur) {
        log.info("Ajoute Administrateur: {}", administrateur);
        administrateurRepository.save(administrateur);
        return administrateurMapper.toDTO(administrateur);
    }

    public AdministrateurResponseDTO findById(Long id) {
        log.info("Find Administrateur by id: {}", id);
        Administrateur administrateur = administrateurRepository.findById(id)
                .orElseThrow(
                        () -> new ItemNotFoundException("Administrateur avec id " + id + " n'existe pas")
                );
        return administrateurMapper.toDTO(administrateur);
    }

    public List<AdministrateurResponseDTO> findAll() {
        log.info("Find All Administrateurs");
        List<Administrateur> administrateurs = administrateurRepository.findAll();
        return administrateurs
                .stream()
                .map(administrateurMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AdministrateurResponseDTO update(Long id, Administrateur administrateur) {
        log.info("Update Administrateur with id: {}", id);
        Administrateur admin =  administrateurRepository.findById(id)
                .orElseThrow(
                        () -> new ItemNotFoundException("Administrateur avec id " + id + " n'existe pas")
                );
        admin.setUsername(administrateur.getUsername());
        admin.setEmail(administrateur.getEmail());
        admin.setPassword(administrateur.getPassword());
        administrateurRepository.save(admin);
        return administrateurMapper.toDTO(admin);
    }

    public void deleteAdministrateur(Long id) {
        log.info("Delete Administrateur with id: {}", id);
        administrateurRepository.deleteById(id);
    }
}
