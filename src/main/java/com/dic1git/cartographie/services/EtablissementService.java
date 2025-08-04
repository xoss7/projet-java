package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.EtablissementDTO;
import com.dic1git.cartographie.dto.EtablissementResponseDTO;
import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.exceptions.ItemNotFoundException;
import com.dic1git.cartographie.mappers.EtablissementMapper;
import com.dic1git.cartographie.repositories.EtablissementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EtablissementService {
    private EtablissementRepository etablissementRepository;
    private EtablissementMapper etablissementMapper;

    public EtablissementResponseDTO save(Etablissement etablissement) {
        etablissementRepository.save(etablissement);
        return etablissementMapper.toDTO(etablissement);
    }

    public EtablissementResponseDTO findById(Long id) {
        Etablissement etablissement = etablissementRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Etablissement avec id " + id + " n'existe pas"));
        return etablissementMapper.toDTO(etablissement);
    }

    public List<EtablissementResponseDTO> findAll() {
        List<Etablissement> etablissement = etablissementRepository.findAll();
        return etablissement.stream()
                .map(etablissementMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EtablissementResponseDTO updateById(Long id, Etablissement etablissement) {
        Etablissement updated = etablissementRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Etablissement avec id " + id + " n'existe pas"));
        updated.setNom(etablissement.getNom());
        updated.setTel(etablissement.getTel());
        updated.setRegion(etablissement.getRegion());
        updated.setStatut(etablissement.getStatut());
        updated.setSiteWeb(etablissement.getSiteWeb());
        updated.setEmail(etablissement.getEmail());
        updated.setDescription(etablissement.getDescription());
        updated.setLocalisation(etablissement.getLocalisation());
        updated.setVille(etablissement.getVille());
        etablissementRepository.save(updated);
        return etablissementMapper.toDTO(updated);
    }

    public void deleteById(Long id) {
        etablissementRepository.deleteById(id);
    }
}
