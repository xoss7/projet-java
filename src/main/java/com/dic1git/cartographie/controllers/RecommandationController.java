package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.RecommandationDTO;
import com.dic1git.cartographie.dto.RecommandationResponseDTO;
import com.dic1git.cartographie.entities.Recommandation;
import com.dic1git.cartographie.mappers.RecommandationMapper;
import com.dic1git.cartographie.services.RecommandationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/recommandations")
public class RecommandationController {
    private final RecommandationMapper recommandationMapper;
    private RecommandationService recommandationService;

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

   @GetMapping("/{id}")
   public ResponseEntity<RecommandationResponseDTO> getRecommandation(@PathVariable Long id) {
       RecommandationResponseDTO recommandation = recommandationService.findById(id);
       return ResponseEntity.ok(recommandation);
   }

   @GetMapping
    public ResponseEntity<List<RecommandationResponseDTO>> getAllRecommandations() {
        List<RecommandationResponseDTO> recommandations = recommandationService.findAll();
        return ResponseEntity.ok(recommandations);
   }

   @PutMapping("/{id}")
   public ResponseEntity<RecommandationResponseDTO> update(
           @PathVariable Long id,
           @Validated @RequestBody RecommandationDTO recommandationDTO) {
        Recommandation recommandation = recommandationMapper.toEntity(recommandationDTO);
        RecommandationResponseDTO response = recommandationService.update(id, recommandation);
        return ResponseEntity.accepted().body(response);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommandation(@PathVariable Long id) {
        recommandationService.delete(id);
        return ResponseEntity.noContent().build();
   }
}
