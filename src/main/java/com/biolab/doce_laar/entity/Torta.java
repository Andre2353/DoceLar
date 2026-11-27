package com.biolab.doce_laar.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("TORTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Torta extends Doce {

    private String saborMassa;

    private Boolean ehGelada;
}
