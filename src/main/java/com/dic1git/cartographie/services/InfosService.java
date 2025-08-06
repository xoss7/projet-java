package com.dic1git.cartographie.services;

import com.dic1git.cartographie.dto.InfosDTO;
import com.dic1git.cartographie.dto.InfosResponseDTO;
import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.entities.Infos;
import com.dic1git.cartographie.exceptions.ItemNotFoundException;
import com.dic1git.cartographie.mappers.InfosMapper;
import com.dic1git.cartographie.repositories.EtablissementRepository;
import com.dic1git.cartographie.repositories.InfosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InfosService {
    private InfosRepository infosRepository;
    private EtablissementRepository etablissementRepository;
    private InfosMapper infosMapper;

    @Transactional
    public InfosResponseDTO save(Long idEtablissement, InfosDTO infosDTO) {

        Etablissement etablissement = etablissementRepository.findById(idEtablissement)
                .orElseThrow(() -> new ItemNotFoundException("Etablissement avec id " +idEtablissement+ " n'existe pas"));

        Infos infos = infosMapper.toEntity(infosDTO);
        infos.setEtablissement(etablissement);
        infosRepository.save(infos);
        return infosMapper.toDTO(infos);
    }

    @Transactional
    public InfosResponseDTO updateInfo(Long idEtablissement, Long idInfos, InfosDTO infosDTO) {
        Infos update = infosRepository.findByIdAndEtablissement_Id(idInfos, idEtablissement)
                .orElseThrow(() -> new ItemNotFoundException("Infos avec id " +idInfos+ " de l'établissement " +idEtablissement+ " n'existe pas"));

        update.setTitre(infosDTO.getTitre());
        update.setContenu(infosDTO.getContenu());
        infosRepository.save(update);
        return infosMapper.toDTO(update);
    }

    public InfosResponseDTO findById(Long idEtablissement, Long idInfos) {
        Infos infos = infosRepository.findByIdAndEtablissement_Id(idInfos, idEtablissement)
                .orElseThrow(() -> new ItemNotFoundException("Infos avec id " +idInfos+ " de l'établissement " +idEtablissement+ " n'existe pas"));
        return infosMapper.toDTO(infos);
    }

    public List<InfosResponseDTO> findAllByEtablissement_Id(Long idEtablissement) {
        List<Infos> infos = infosRepository.findAllByEtablissement_Id(idEtablissement);
        return infos.stream()
                .map(infosMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long idEtablissement, Long idInfos) {
        infosRepository.deleteByIdAndEtablissement_Id(idInfos, idEtablissement);
    }
}