package com.biolab.doce_laar.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("BOLO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bolo extends Doce {

    private Double pesoKg;

    private String recheio;
}
