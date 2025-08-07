package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.FormationDTO;
import com.dic1git.cartographie.dto.FormationResponseDTO;
import com.dic1git.cartographie.services.FormationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Endpoint pour les formations des établissements")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class FormationController {
    private FormationService formationService;

    @Operation(summary = "Ajouter une nouvelle formation pour un établissement")
    @PostMapping("/etablissements/{idEtablissement}/formations")
    public ResponseEntity<FormationResponseDTO> save(
            @PathVariable Long idEtablissement, @Validated @RequestBody FormationDTO formationDTO) {
        FormationResponseDTO response = formationService.save(formationDTO, idEtablissement);
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Afficher une formation d'un établissement")
    @GetMapping("/etablissements/{idEtablissement}/formations/{idFormation}")
    public ResponseEntity<FormationResponseDTO> getById(@PathVariable Long idEtablissement, @PathVariable Long idFormation) {
        FormationResponseDTO response = formationService.findById(idEtablissement, idFormation);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lister toutes les formations proposées par un établissement")
    @GetMapping("/etablissements/{idEtablissement}/formations")
    public ResponseEntity<List<FormationResponseDTO>> getAll(@PathVariable Long idEtablissement) {
        List<FormationResponseDTO> response = formationService.findAllByEtablissementId(idEtablissement);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listes toutes les formations de tous les établissements. Utile pour des statistiques")
    @GetMapping("/formations")
    public ResponseEntity<List<FormationResponseDTO>> getAll() {
        List<FormationResponseDTO> response = formationService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mettre à jour une formation d'un établissement")
    @PutMapping("/etablissements/{idEtablissement}/formations/{idFormation}")
    public  ResponseEntity<FormationResponseDTO> update(
            @PathVariable Long idEtablissement,
            @PathVariable Long idFormation,
            @Validated @RequestBody FormationDTO formationDTO) {
        FormationResponseDTO response = formationService.updateById(idEtablissement, idFormation, formationDTO);
        return ResponseEntity.accepted().body(response);
    }

    @Operation(summary = "Supprimer une formation d'un établissement")
    @DeleteMapping("/etablissements/{idEtablissement}/formations/{idFormation}")
    public void deleteById(@PathVariable Long idEtablissement, @PathVariable Long idFormation) {
        formationService.deleteById(idEtablissement, idFormation);
    }
}
