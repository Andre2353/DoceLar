package com.biolab.doce_laar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {
    private Long clienteId;
    private Long funcionarioId;
    private Boolean ehEncomenda;
    private LocalDateTime dataEntrega;
    private List<ItemPedidoRequestDTO> itens;
}
