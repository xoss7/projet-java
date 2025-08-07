package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.RecommandationDTO;
import com.dic1git.cartographie.dto.RecommandationResponseDTO;
import com.dic1git.cartographie.entities.Recommandation;
import com.dic1git.cartographie.mappers.RecommandationMapper;
import com.dic1git.cartographie.services.RecommandationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Endpoint pour les recommandations")
@RestController
@AllArgsConstructor
@RequestMapping("/api/recommandations")
public class RecommandationController {
    private final RecommandationMapper recommandationMapper;
    private RecommandationService recommandationService;

    @Operation(summary = "Recommander un établissement")
    @PostMapping
    public ResponseEntity<RecommandationResponseDTO> save(
            @RequestBody RecommandationDTO recommandationDTO
    ) {
        Recommandation recommandation = recommandationMapper.toEntity(recommandationDTO);
        RecommandationResponseDTO response = recommandationService.save(recommandation);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recommandation.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Lire une recommandation")
    @GetMapping("/{id}")
    public ResponseEntity<RecommandationResponseDTO> getRecommandation(@PathVariable Long id) {
        RecommandationResponseDTO recommandation = recommandationService.findById(id);
        return ResponseEntity.ok(recommandation);
    }

    @Operation(summary = "Lister toutes les recommandations faites")
    @GetMapping
    public ResponseEntity<List<RecommandationResponseDTO>> getAllRecommandations() {
        List<RecommandationResponseDTO> recommandations = recommandationService.findAll();
        return ResponseEntity.ok(recommandations);
   }

   @Operation(summary = "Modifier une recommandation")
   @PutMapping("/{id}")
   public ResponseEntity<RecommandationResponseDTO> update(
           @PathVariable Long id,
           @Validated @RequestBody RecommandationDTO recommandationDTO) {
        Recommandation recommandation = recommandationMapper.toEntity(recommandationDTO);
        RecommandationResponseDTO response = recommandationService.update(id, recommandation);
        return ResponseEntity.accepted().body(response);
   }

   @Operation(summary = "Supprimer une recommandation")
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommandation(@PathVariable Long id) {
        recommandationService.delete(id);
        return ResponseEntity.noContent().build();
   }
}
