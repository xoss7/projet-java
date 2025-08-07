package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.PartenaireDTO;
import com.dic1git.cartographie.dto.PartenaireResponseDTO;
import com.dic1git.cartographie.entities.Partenaire;
import com.dic1git.cartographie.mappers.PartenaireMapper;
import com.dic1git.cartographie.repositories.PartenaireRepository;
import com.dic1git.cartographie.utils.EntityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartenaireService {

    private PartenaireRepository partenaireRepository;
    private PartenaireMapper partenaireMapper;

    public PartenaireResponseDTO save(PartenaireDTO partenaireDTO) {
        Partenaire partenaire = partenaireMapper.toEntity(partenaireDTO);
        partenaireRepository.save(partenaire);
        return partenaireMapper.toDTO(partenaire);
    }

    public PartenaireResponseDTO updateById(Long id, PartenaireDTO partenaireDTO) {
        Partenaire updated = EntityUtils.getEntityOrThrow(id, partenaireRepository, "Partenaire");
        updated.setNom(partenaireDTO.getNom());
        updated.setEmail(partenaireDTO.getEmail());
        updated.setSecteur(partenaireDTO.getSecteur());
        return partenaireMapper.toDTO(partenaireRepository.save(updated));
    }

    public PartenaireResponseDTO findById(Long id) {
        Partenaire partenaire = EntityUtils.getEntityOrThrow(id, partenaireRepository, "Partenaire");
        return partenaireMapper.toDTO(partenaire);
    }

    public List<PartenaireResponseDTO> findAll() {
        List<Partenaire> partenaires = partenaireRepository.findAll();
        return partenaires.stream()
                .map(partenaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        EntityUtils.entityExistsOrThrow(id,  partenaireRepository, "Partenaire");
        partenaireRepository.deleteById(id);
    }
}