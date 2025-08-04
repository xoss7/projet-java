package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.EtablissementDTO;
import com.dic1git.cartographie.dto.EtablissementResponseDTO;
import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.mappers.EtablissementMapper;
import com.dic1git.cartographie.services.EtablissementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/etablissement/")
@AllArgsConstructor
public class EtablissementController {
    private EtablissementService etablissementService;
    private EtablissementMapper etablissementMapper;

    @PostMapping
    public ResponseEntity<EtablissementResponseDTO> createEtablissement(@Validated @RequestBody EtablissementDTO etablissementDTO) {
        Etablissement etablissement = etablissementMapper.toEntity(etablissementDTO);
        EtablissementResponseDTO response = etablissementService.save(etablissement);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EtablissementResponseDTO>> getAllEtablissement() {
        List<EtablissementResponseDTO> etablissements = etablissementService.findAll();
        return ResponseEntity.ok(etablissements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtablissementResponseDTO> getEtablissement(@PathVariable Long id) {
        EtablissementResponseDTO response = etablissementService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtablissementResponseDTO> updateEtablissement(
            @PathVariable Long id, @Validated @RequestBody EtablissementDTO etablissementDTO
    ) {
        Etablissement etablissement = etablissementMapper.toEntity(etablissementDTO);
        EtablissementResponseDTO response = etablissementService.updateById(id, etablissement);
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EtablissementResponseDTO> deleteEtablissement(@PathVariable Long id) {
        etablissementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
