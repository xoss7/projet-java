package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.EtablissementResponseDTO;
import com.dic1git.cartographie.dto.PartenaireResponseDTO;
import com.dic1git.cartographie.dto.PartenariatDTO;
import com.dic1git.cartographie.dto.PartenariatResponseDTO;
import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.entities.Partenaire;
import com.dic1git.cartographie.entities.Partenariat;
import com.dic1git.cartographie.exceptions.ItemNotFoundException;
import com.dic1git.cartographie.mappers.EtablissementMapper;
import com.dic1git.cartographie.mappers.PartenaireMapper;
import com.dic1git.cartographie.mappers.PartenariatMapper;
import com.dic1git.cartographie.repositories.EtablissementRepository;
import com.dic1git.cartographie.repositories.PartenaireRepository;
import com.dic1git.cartographie.repositories.PartenariatRepository;
import com.dic1git.cartographie.utils.EntityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartenariatService {

    private EtablissementMapper etablissementMapper;
    private PartenariatRepository partenariatRepository;
    private PartenariatMapper partenariatMapper;
    private EtablissementRepository etablissementRepository;
    private PartenaireRepository partenaireRepository;
    private PartenaireMapper partenaireMapper;

    public PartenariatResponseDTO save(PartenariatDTO partenariatDTO) {
        Etablissement etab = EntityUtils.getEntityOrThrow(
                partenariatDTO.getEtablissementId(), etablissementRepository, "Etablissement"
        );
        Partenaire partenaire = EntityUtils.getEntityOrThrow(
                partenariatDTO.getPartenaireId(), partenaireRepository, "Partenaire"
        );
        Partenariat partenariat = partenariatMapper.toEntity(partenariatDTO);
        partenariat.setEtablissement(etab);
        partenariat.setPartenaire(partenaire);
        partenariatRepository.save(partenariat);
        return partenariatMapper.toDTO(partenariat);
    }

    public PartenariatResponseDTO findById(Long id) {
        Partenariat p = EntityUtils.getEntityOrThrow(
                id, partenariatRepository, "Etablissement"
        );
        return partenariatMapper.toDTO(p);
    }

    public List<PartenaireResponseDTO> findPartenaireByEtablissementId(Long idEtablissement) {
        EntityUtils.entityExistsOrThrow(idEtablissement, etablissementRepository, "Etablissement");
        return partenariatRepository.findPartenaireByEtablissementId(idEtablissement)
                .stream()
                .map(partenaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EtablissementResponseDTO> findEtablissementByPartenaireId(Long idPartenaire) {
        EntityUtils.entityExistsOrThrow(idPartenaire, partenaireRepository, "Partenaire");
        return partenariatRepository.findEtablissementByPartenaireId(idPartenaire)
                .stream()
                .map(etablissementMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PartenariatResponseDTO updateById(Long id, PartenariatDTO partenariatDTO) {
        Partenariat updated = EntityUtils.getEntityOrThrow(id, partenariatRepository, "Partenariat");
        Etablissement e = EntityUtils.getEntityOrThrow(partenariatDTO.getEtablissementId(), etablissementRepository, "Etablissement");
        Partenaire p = EntityUtils.getEntityOrThrow(partenariatDTO.getPartenaireId(), partenaireRepository, "Partenaire");
        updated.setEtablissement(e);
        updated.setPartenaire(p);
        updated.setType(partenariatDTO.getType());
        updated.setDateDebut(partenariatDTO.getDateDebut());
        partenariatRepository.save(updated);
        return partenariatMapper.toDTO(updated);
    }

    public void deleteById(Long id) throws ItemNotFoundException {
        EntityUtils.entityExistsOrThrow(id, partenariatRepository, "Partenariat");
        partenariatRepository.deleteById(id);
    }

}