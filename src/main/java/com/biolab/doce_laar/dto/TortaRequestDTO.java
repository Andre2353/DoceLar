package com.biolab.doce_laar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TortaRequestDTO {
    private String nome;
    private BigDecimal precoBase;
    private Boolean ativo;
    private String saborMassa;
    private Boolean ehGelada;
}
