package com.biolab.doce_laar.dto;

import com.biolab.doce_laar.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarStatusPedidoDTO {
    private StatusPedido status;
}
