package com.biolab.doce_laar.config;

import com.biolab.doce_laar.entity.Bolo;
import com.biolab.doce_laar.entity.Cliente;
import com.biolab.doce_laar.entity.Funcionario;
import com.biolab.doce_laar.entity.ItemPedido;
import com.biolab.doce_laar.entity.Pedido;
import com.biolab.doce_laar.entity.Torta;
import com.biolab.doce_laar.enums.Cargo;
import com.biolab.doce_laar.enums.StatusPedido;
import com.biolab.doce_laar.repository.ClienteRepository;
import com.biolab.doce_laar.repository.DoceRepository;
import com.biolab.doce_laar.repository.FuncionarioRepository;
import com.biolab.doce_laar.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final DoceRepository doceRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) {
        if (clienteRepository.count() == 0) {
            Cliente cliente = clienteRepository.save(
                    new Cliente(null, "Ana Beatriz Souza", "(11) 99123-4567")
            );

            Funcionario funcionario = funcionarioRepository.save(
                    new Funcionario(null, "Carlos Eduardo Lima", Cargo.ATENDENTE)
            );

            Bolo bolo = new Bolo(1.5, "Brigadeiro Gourmet");
            bolo.setNome("Bolo de Brigadeiro Belga");
            bolo.setPrecoBase(new BigDecimal("85.00"));
            bolo.setAtivo(true);
            bolo = doceRepository.save(bolo);

            Torta torta = new Torta("Massa Sablée", true);
            torta.setNome("Torta de Limão Siciliano");
            torta.setPrecoBase(new BigDecimal("70.00"));
            torta.setAtivo(true);
            torta = doceRepository.save(torta);

            ItemPedido item1 = new ItemPedido(null, bolo.getPrecoBase(), 1, bolo);
            ItemPedido item2 = new ItemPedido(null, torta.getPrecoBase(), 1, torta);

            Pedido pedido = new Pedido(
                    null,
                    LocalDateTime.now(),
                    StatusPedido.PENDENTE,
                    true,
                    LocalDateTime.now().plusDays(1),
                    cliente,
                    funcionario,
                    new ArrayList<>(List.of(item1, item2))
            );

            pedidoRepository.save(pedido);
        }
    }
}
