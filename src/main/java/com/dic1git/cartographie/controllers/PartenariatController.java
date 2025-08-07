package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.EtablissementResponseDTO;
import com.dic1git.cartographie.dto.PartenaireResponseDTO;
import com.dic1git.cartographie.dto.PartenariatDTO;
import com.dic1git.cartographie.dto.PartenariatResponseDTO;
import com.dic1git.cartographie.mappers.PartenariatMapper;
import com.dic1git.cartographie.services.PartenariatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PartenariatController {

    private PartenariatService partenariatService;

    @PostMapping
    public ResponseEntity<PartenariatResponseDTO> save(@Validated @RequestBody PartenariatDTO partenariatDTO) {
        PartenariatResponseDTO response = partenariatService.save(partenariatDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/partenariats/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/partenariats/{id}")
    public ResponseEntity<PartenariatResponseDTO> update(@PathVariable Long id,
                                                         @Validated @RequestBody PartenariatDTO partenariatDTO) {
        PartenariatResponseDTO response = partenariatService.updateById(id, partenariatDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/etablissements/{id}/partenaires")
    public ResponseEntity<List<PartenaireResponseDTO>> getPartenaires(@PathVariable Long id) {
        List<PartenaireResponseDTO> response = partenariatService.findPartenaireByEtablissementId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/partenaires/{id}/etablissements")
    public ResponseEntity<List<EtablissementResponseDTO>> getEtablissements(@PathVariable Long id) {
        List<EtablissementResponseDTO> response = partenariatService.findEtablissementByPartenaireId(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/partenariats/{id}")
    public ResponseEntity<?> deletePartenariat(@PathVariable Long id) {
        partenariatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
