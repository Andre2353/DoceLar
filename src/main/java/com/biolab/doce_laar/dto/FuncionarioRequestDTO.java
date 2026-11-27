package com.biolab.doce_laar.dto;

import com.biolab.doce_laar.enums.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioRequestDTO {
    private String nome;
    private Cargo cargo;
}
