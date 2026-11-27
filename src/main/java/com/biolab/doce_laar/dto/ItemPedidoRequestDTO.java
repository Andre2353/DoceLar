package com.biolab.doce_laar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoRequestDTO {
    private Long doceId;
    private Integer quantidade;
}
