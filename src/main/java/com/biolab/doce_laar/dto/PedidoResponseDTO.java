package com.biolab.doce_laar.dto;

import com.biolab.doce_laar.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private StatusPedido status;
    private Boolean ehEncomenda;
    private LocalDateTime dataEntrega;
    private String nomeCliente;
    private String nomeFuncionario;
    private List<ItemPedidoResponseDTO> itens;
}
