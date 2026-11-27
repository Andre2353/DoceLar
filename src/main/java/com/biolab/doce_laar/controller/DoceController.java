package com.biolab.doce_laar.controller;

import com.biolab.doce_laar.dto.BoloRequestDTO;
import com.biolab.doce_laar.dto.DoceResponseDTO;
import com.biolab.doce_laar.dto.TortaRequestDTO;
import com.biolab.doce_laar.service.DoceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doces")
@RequiredArgsConstructor
public class DoceController {

    private final DoceService doceService;

    @GetMapping
    public ResponseEntity<List<DoceResponseDTO>> listarTodos() {
        return ResponseEntity.ok(doceService.listarTodos());
    }

    @PostMapping("/bolo")
    public ResponseEntity<DoceResponseDTO> criarBolo(@RequestBody BoloRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doceService.criarBolo(dto));
    }

    @PostMapping("/torta")
    public ResponseEntity<DoceResponseDTO> criarTorta(@RequestBody TortaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doceService.criarTorta(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        doceService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
