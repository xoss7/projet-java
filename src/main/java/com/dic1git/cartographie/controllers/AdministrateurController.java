package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.AdministrateurDTO;
import com.dic1git.cartographie.dto.AdministrateurResponseDTO;
import com.dic1git.cartographie.entities.Administrateur;
import com.dic1git.cartographie.mappers.AdministrateurMapper;
import com.dic1git.cartographie.services.AdministrateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/administrateurs")
@Tag(name = "Gestion des administrateurs", description = "Endpoint pour les administrateurs de la plateforme")
public class AdministrateurController {
    private AdministrateurService administrateurService;
    private AdministrateurMapper administrateurMapper;

    @Operation(summary = "Créer un administrateur", description = "Ajouter un nouveau administrateur de la plateforme")
    @PostMapping
    public ResponseEntity<AdministrateurResponseDTO> createAdministrateur(@Validated @RequestBody AdministrateurDTO administrateurDTO) {
        Administrateur administrateur = administrateurMapper.toEntity(administrateurDTO);
        AdministrateurResponseDTO response = administrateurService.save(administrateur);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Chercher un administrateur par son id")
    @GetMapping("/{id}")
    public ResponseEntity<AdministrateurResponseDTO> findById(@PathVariable Long id) {
        AdministrateurResponseDTO response = administrateurService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Chercher tous les administrateurs")
    @GetMapping
    public ResponseEntity<List<AdministrateurResponseDTO>> findAll() {
        List<AdministrateurResponseDTO> admins = administrateurService.findAll();
        return ResponseEntity.ok(admins);
    }

    @Operation(summary = "Mettre à jour les infos d'un administrateur par son id")
    @PutMapping("/{id}")
    public ResponseEntity<AdministrateurResponseDTO> updateAdministrateur(
            @Validated @RequestBody AdministrateurDTO administrateurDTO,
            @PathVariable Long id) {

        Administrateur administrateur = administrateurMapper.toEntity(administrateurDTO);
        AdministrateurResponseDTO response = administrateurService.update(id, administrateur);
        return ResponseEntity.accepted().body(response);
    }

    @Operation(summary = "Supprimer un administrateur de la plateforme")
    @DeleteMapping("/{id}")
    public ResponseEntity<AdministrateurResponseDTO> deleteAdministrateur(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
        return ResponseEntity.noContent().build();
    }
}
