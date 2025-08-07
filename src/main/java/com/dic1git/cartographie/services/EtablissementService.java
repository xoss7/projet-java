package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.EtablissementResponseDTO;
import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.mappers.EtablissementMapper;
import com.dic1git.cartographie.repositories.EtablissementRepository;
import com.dic1git.cartographie.utils.EntityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EtablissementService {
    private EtablissementRepository etablissementRepository;
    private EtablissementMapper etablissementMapper;

    @Transactional
    public EtablissementResponseDTO save(Etablissement etablissement) {
        etablissementRepository.save(etablissement);
        return etablissementMapper.toDTO(etablissement);
    }

    public EtablissementResponseDTO findById(Long id) {
        Etablissement etablissement = EntityUtils.getEntityOrThrow(id, etablissementRepository, "Etablissement");
        return etablissementMapper.toDTO(etablissement);
    }

    public List<EtablissementResponseDTO> findAll() {
        List<Etablissement> etablissement = etablissementRepository.findAll();
        return etablissement.stream()
                .map(etablissementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EtablissementResponseDTO updateById(Long id, Etablissement etablissement) {
        Etablissement updated = EntityUtils.getEntityOrThrow(id, etablissementRepository, "Etablissement");
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

    @Transactional
    public void deleteById(Long id) {
        EntityUtils.getEntityOrThrow(id, etablissementRepository, "Etablissement");
        etablissementRepository.deleteById(id);
    }
}
