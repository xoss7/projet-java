package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.FormationDTO;
import com.dic1git.cartographie.dto.FormationResponseDTO;
import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.entities.Formation;
import com.dic1git.cartographie.exceptions.ItemNotFoundException;
import com.dic1git.cartographie.mappers.FormationMapper;
import com.dic1git.cartographie.repositories.EtablissementRepository;
import com.dic1git.cartographie.repositories.FormationRepository;
import com.dic1git.cartographie.utils.EntityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FormationService {
    FormationRepository formationRepository;
    FormationMapper formationMapper;
    EtablissementRepository etablissementRepository;

    @Transactional
    public FormationResponseDTO save(FormationDTO formationDTO, Long idEtablissement) throws ItemNotFoundException {
        Etablissement etablissement = EntityUtils.getEntityOrThrow(idEtablissement, etablissementRepository, "Etablissement");
        Formation formation = formationMapper.toEntity(formationDTO);
        formation.setEtablissement(etablissement);
        formationRepository.save(formation);
        return formationMapper.toDTO(formation);
    }

    public FormationResponseDTO findById(Long idEtablissement, Long idFormation) {
        EntityUtils.entityExistsOrThrow(idEtablissement, etablissementRepository, "Etablissement");
        Formation formation =  formationRepository.findByIdAndEtablissement_Id(idFormation, idEtablissement)
                .orElseThrow(
                        () -> new ItemNotFoundException("Formation avec id " + idFormation + " de l'établissement " + idEtablissement + " n'existe pas")
                );
        return formationMapper.toDTO(formation);
    }

    public List<FormationResponseDTO> findAllByEtablissementId(Long idEtablissement) {
        EntityUtils.entityExistsOrThrow(idEtablissement, etablissementRepository, "Etablissement");
        List<Formation> formations = formationRepository.findByEtablissement_Id(idEtablissement);
        return formations.stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<FormationResponseDTO> findAll() {
        List<Formation> formations = formationRepository.findAll();
        return formations.stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FormationResponseDTO updateById(Long idEtablissement, Long idFormation, FormationDTO formationDTO) {
        EntityUtils.entityExistsOrThrow(idEtablissement, etablissementRepository, "Etablissement");
        Formation updated = formationRepository.findByIdAndEtablissement_Id(idFormation, idEtablissement)
                .orElseThrow(
                        () -> new ItemNotFoundException("Formation avec id " + idFormation + " de l'établissement " + idEtablissement + " n'existe pas")
                );
        updated.setNom(formationDTO.getNom());
        updated.setDescription(formationDTO.getDescription());
        formationRepository.save(updated);
        return formationMapper.toDTO(updated);
    }

    @Transactional
    public void deleteById(Long idEtablissement, Long idFormation) {
        formationRepository.findByIdAndEtablissement_Id(idFormation, idEtablissement)
                .orElseThrow(
                        () -> new ItemNotFoundException("Formation avec id " + idFormation + " de l'établissement " + idEtablissement + " n'existe pas")
                );
        formationRepository.deleteById(idFormation);
    }


}