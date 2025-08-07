package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.PartenaireDTO;
import com.dic1git.cartographie.dto.PartenaireResponseDTO;
import com.dic1git.cartographie.services.PartenaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Endpoint pour les partenaires des établissements")
@RestController
@AllArgsConstructor
@RequestMapping("/api/partenaires")
public class PartenaireController {

    private PartenaireService partenaireService;


    @Operation(summary = "Ajouter un partenaire ")
    @PostMapping
    public ResponseEntity<PartenaireResponseDTO> save(@Validated @RequestBody PartenaireDTO partenaireDTO) {
        PartenaireResponseDTO response = partenaireService.save(partenaireDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Lister les partenaires des établissements")
    @GetMapping
    public ResponseEntity<List<PartenaireResponseDTO>> findAll() {
        List<PartenaireResponseDTO> response = partenaireService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Afficher un partenaire")
    @GetMapping("/{id}")
    public ResponseEntity<PartenaireResponseDTO> findById(@PathVariable Long id) {
        PartenaireResponseDTO response = partenaireService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mettre à ajour un partenaire")
    @PutMapping("/{id}")
    public ResponseEntity<PartenaireResponseDTO> updateById(@PathVariable Long id,
                                                            @Validated @RequestBody PartenaireDTO partenaireDTO) {
        PartenaireResponseDTO response = partenaireService.updateById(id, partenaireDTO);
        return ResponseEntity.accepted().body(response);
    }

    @Operation(summary = "Supprimer un partenaire")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        partenaireService.deleteById(id);
    }

}
