package com.biolab.doce_laar;

import com.biolab.doce_laar.entity.Bolo;
import com.biolab.doce_laar.entity.Cliente;
import com.biolab.doce_laar.entity.Funcionario;
import com.biolab.doce_laar.entity.ItemPedido;
import com.biolab.doce_laar.entity.Pedido;
import com.biolab.doce_laar.enums.Cargo;
import com.biolab.doce_laar.enums.StatusPedido;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoceLaarApplicationTests {

    @Test
    void deveInstanciarEntidadesCorretamente() {
        Cliente cliente = new Cliente(1L, "Ana Beatriz Souza", "(11) 99123-4567");
        Funcionario funcionario = new Funcionario(1L, "Carlos Eduardo Lima", Cargo.ATENDENTE);

        Bolo bolo = new Bolo(1.5, "Brigadeiro");
        bolo.setId(1L);
        bolo.setNome("Bolo de Brigadeiro");
        bolo.setPrecoBase(new BigDecimal("85.00"));
        bolo.setAtivo(true);

        ItemPedido item = new ItemPedido(1L, bolo.getPrecoBase(), 2, bolo);

        Pedido pedido = new Pedido(
                1L,
                LocalDateTime.now(),
                StatusPedido.PENDENTE,
                true,
                LocalDateTime.now().plusDays(1),
                cliente,
                funcionario,
                List.of(item)
        );

        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
        assertEquals(1, pedido.getItens().size());
    }
}
