package com.biolab.doce_laar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoResponseDTO {
    private Long id;
    private Long doceId;
    private String nomeDoce;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
