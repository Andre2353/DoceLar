package com.biolab.doce_laar.controller;

import com.biolab.doce_laar.dto.AtualizarStatusPedidoDTO;
import com.biolab.doce_laar.dto.PedidoRequestDTO;
import com.biolab.doce_laar.dto.PedidoResponseDTO;
import com.biolab.doce_laar.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criarPedido(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestBody AtualizarStatusPedidoDTO dto) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, dto));
    }
}
