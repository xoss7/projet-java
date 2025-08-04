package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.RecommandationResponseDTO;
import com.dic1git.cartographie.entities.Recommandation;
import com.dic1git.cartographie.exceptions.ItemNotFoundException;
import com.dic1git.cartographie.mappers.RecommandationMapper;
import com.dic1git.cartographie.repositories.RecommandationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecommandationService {
    private RecommandationRepository recommandationRepository;
    private RecommandationMapper recommandationMapper;

    @Transactional
    public RecommandationResponseDTO save(Recommandation recommandation) {
        recommandationRepository.save(recommandation);
        return recommandationMapper.toDTO(recommandation);
    }

    public RecommandationResponseDTO findById(Long id) {
        Recommandation recommandation = recommandationRepository.findById(id)
                .orElseThrow(
                        () -> new ItemNotFoundException("Recommandation avec id " + id + " n'existe pas")
                );
        return recommandationMapper.toDTO(recommandation);
    }

    public List<RecommandationResponseDTO> findAll() {
        List<Recommandation> recommandations = recommandationRepository.findAll();
        return recommandations
                .stream()
                .map(recommandation -> recommandationMapper.toDTO(recommandation))
                .collect(Collectors.toList());
    }

    public RecommandationResponseDTO update(Long id, Recommandation recommandation) {
        Recommandation updated =  recommandationRepository.findById(id)
                .orElseThrow(
                        () -> new ItemNotFoundException("Recommandation avec id " + id + " n'existe pas")
                );
        updated.setNomEtablissement(recommandation.getNomEtablissement());
        updated.setEmail(recommandation.getEmail());
        updated.setTelephone(recommandation.getTelephone());
        recommandationRepository.save(updated);
        return recommandationMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Recommandation recommandation = recommandationRepository.findById(id)
                .orElseThrow(
                        () -> new ItemNotFoundException("Recommandation avec id " + id + " n'existe pas")
                );
        recommandationRepository.delete(recommandation);
    }
}