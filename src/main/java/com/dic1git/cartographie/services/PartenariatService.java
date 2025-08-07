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

    private Etablissement getEtablissementOrThrow(final Long id) {
        return etablissementRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Etablissement " +id+ " n'existe pas"));
    }

    private void etablissementExistsOrThrow(final Long id) throws ItemNotFoundException {
        if (!etablissementRepository.existsById(id)) {
            throw new ItemNotFoundException("Etablissement " +id+ " n'existe pas");
        }
    }

    private Partenaire getPartenaireOrThrow(final Long id) {
        return partenaireRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Partenaire " +id+ " n'existe pas"));
    }

    private void partenaireExistsOrThrow(final Long id) throws ItemNotFoundException {
        if (!partenaireRepository.existsById(id)) {
            throw new ItemNotFoundException("Partenaire " +id+ " n'existe pas");
        }
    }

    public PartenariatResponseDTO save(PartenariatDTO partenariatDTO) {
        Etablissement etab = getEtablissementOrThrow(partenariatDTO.getEtablissementId());
        Partenaire partenaire = getPartenaireOrThrow(partenariatDTO.getPartenaireId());
        Partenariat partenariat = partenariatMapper.toEntity(partenariatDTO);
        partenariat.setEtablissement(etab);
        partenariat.setPartenaire(partenaire);
        partenariatRepository.save(partenariat);
        return partenariatMapper.toDTO(partenariat);
    }

    public PartenariatResponseDTO findById(Long id) {
        Partenariat p = partenariatRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Partenariat " +id+ " n'existe pas"));
        return partenariatMapper.toDTO(p);
    }

    public List<PartenaireResponseDTO> findPartenaireByEtablissementId(Long idEtablissement) {
        etablissementExistsOrThrow(idEtablissement);

        return partenariatRepository.findPartenaireByEtablissementId(idEtablissement)
                .stream()
                .map(partenaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EtablissementResponseDTO> findEtablissementByPartenaireId(Long idPartenaire) {
        partenaireExistsOrThrow(idPartenaire);
        return partenariatRepository.findEtablissementByPartenaireId(idPartenaire)
                .stream()
                .map(etablissementMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PartenariatResponseDTO updateById(Long id, PartenariatDTO partenariatDTO) {
        Partenariat updated = partenariatRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Partenariat " +id+ " n'existe pas"));
        updated.setEtablissement(getEtablissementOrThrow(partenariatDTO.getEtablissementId()));
        updated.setPartenaire(getPartenaireOrThrow(partenariatDTO.getPartenaireId()));
        updated.setType(partenariatDTO.getType());
        updated.setDateDebut(partenariatDTO.getDateDebut());
        partenariatRepository.save(updated);
        return partenariatMapper.toDTO(updated);
    }

    public void deleteById(Long id) throws ItemNotFoundException {
        if (!partenariatRepository.existsById(id)) {
            throw new ItemNotFoundException("Partenariat " +id+ " n'existe pas");
        }
        partenariatRepository.deleteById(id);
    }

}
