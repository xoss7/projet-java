package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.AdministrateurDTO;
import com.dic1git.cartographie.dto.AdministrateurResponseDTO;
import com.dic1git.cartographie.entities.Administrateur;
import com.dic1git.cartographie.mappers.AdministrateurMapper;
import com.dic1git.cartographie.services.AdministrateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {
    private AdministrateurService administrateurService;
    private AdministrateurMapper administrateurMapper;

    @PostMapping
    public ResponseEntity<AdministrateurResponseDTO> createAdministrateur(@Validated @RequestBody AdministrateurDTO administrateurDTO) {
        log.info("CREATE - ADMINISTRATEUR");
        Administrateur administrateur = administrateurMapper.toEntity(administrateurDTO);
        AdministrateurResponseDTO response = administrateurService.save(administrateur);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrateurResponseDTO> findById(@PathVariable Long id) {
        log.info("GET - ADMINISTRATEUR");
        AdministrateurResponseDTO response = administrateurService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AdministrateurResponseDTO>> findAll() {
        log.info("GET - ALL ADMINISTRATEURS");
        List<AdministrateurResponseDTO> admins = administrateurService.findAll();
        return ResponseEntity.ok(admins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrateurResponseDTO> updateAdministrateur(
            @Validated @RequestBody AdministrateurDTO administrateurDTO,
            @PathVariable Long id) {

        log.info("PUT - ADMINISTRATEUR");
        Administrateur administrateur = administrateurMapper.toEntity(administrateurDTO);
        AdministrateurResponseDTO response = administrateurService.update(id, administrateur);
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdministrateurResponseDTO> deleteAdministrateur(@PathVariable Long id) {
        log.info("DELETE - ADMINISTRATEUR");
        administrateurService.deleteAdministrateur(id);
        return ResponseEntity.noContent().build();
    }
}
