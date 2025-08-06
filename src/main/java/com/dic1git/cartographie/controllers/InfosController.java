package com.dic1git.cartographie.controllers;

import com.dic1git.cartographie.dto.InfosDTO;
import com.dic1git.cartographie.dto.InfosResponseDTO;
import com.dic1git.cartographie.mappers.InfosMapper;
import com.dic1git.cartographie.services.InfosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/etablissements/{idEtablissement}/infos")
public class InfosController {

    private InfosService infosService;
    private InfosMapper infosMapper;

    @PostMapping
    public ResponseEntity<InfosResponseDTO> save(
            @PathVariable Long idEtablissement,
            @Validated @RequestBody InfosDTO infosDTO
    ) {
        InfosResponseDTO response = infosService.save(idEtablissement, infosDTO);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{idInfos}")
    public ResponseEntity<InfosResponseDTO> getInfos(@PathVariable Long idEtablissement, @PathVariable Long idInfos) {
        InfosResponseDTO response = infosService.findById(idEtablissement, idInfos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InfosResponseDTO>> getAllInfos(@PathVariable Long idEtablissement) {
        List<InfosResponseDTO> response = infosService.findAllByEtablissement_Id(idEtablissement);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idInfos}")
    public ResponseEntity<InfosResponseDTO> updateInfos(
            @PathVariable Long idEtablissement,
            @PathVariable Long idInfos,
            @Validated @RequestBody InfosDTO infosDTO
    ) {
        InfosResponseDTO response = infosService.updateInfo(idEtablissement, idInfos, infosDTO);
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{idInfos}")
    public void deleteInfos(@PathVariable Long idEtablissement, @PathVariable Long idInfos) {
        infosService.deleteById(idEtablissement, idInfos);
    }

}
