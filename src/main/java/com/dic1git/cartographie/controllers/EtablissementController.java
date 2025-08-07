package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.EtablissementDTO;
import com.dic1git.cartographie.dto.EtablissementResponseDTO;
import com.dic1git.cartographie.entities.Etablissement;
import com.dic1git.cartographie.mappers.EtablissementMapper;
import com.dic1git.cartographie.services.EtablissementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Endpoint pour les établissements")
@RestController
@RequestMapping("/api/etablissement/")
@AllArgsConstructor
public class EtablissementController {
    private EtablissementService etablissementService;
    private EtablissementMapper etablissementMapper;

    @Operation(summary = "Ajouter un nouveau établissement à la plateforme")
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

    @Operation(summary = "Lire tous les établissements de la plateforme")
    @GetMapping
    public ResponseEntity<List<EtablissementResponseDTO>> getAllEtablissement() {
        List<EtablissementResponseDTO> etablissements = etablissementService.findAll();
        return ResponseEntity.ok(etablissements);
    }

    @Operation(summary = "Chercher un établissement par son id")
    @GetMapping("/{id}")
    public ResponseEntity<EtablissementResponseDTO> getEtablissement(@PathVariable Long id) {
        EtablissementResponseDTO response = etablissementService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mettre à jour les informations d'un établissement")
    @PutMapping("/{id}")
    public ResponseEntity<EtablissementResponseDTO> updateEtablissement(
            @PathVariable Long id, @Validated @RequestBody EtablissementDTO etablissementDTO
    ) {
        Etablissement etablissement = etablissementMapper.toEntity(etablissementDTO);
        EtablissementResponseDTO response = etablissementService.updateById(id, etablissement);
        return ResponseEntity.accepted().body(response);
    }

    @Operation(summary = "Supprimer un établissement de la plateforme")
    @DeleteMapping("/{id}")
    public ResponseEntity<EtablissementResponseDTO> deleteEtablissement(@PathVariable Long id) {
        etablissementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
