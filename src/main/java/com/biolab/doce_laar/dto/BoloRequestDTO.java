package com.biolab.doce_laar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoloRequestDTO {
    private String nome;
    private BigDecimal precoBase;
    private Boolean ativo;
    private Double pesoKg;
    private String recheio;
}
