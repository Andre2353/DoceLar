package com.biolab.doce_laar.service;

import com.biolab.doce_laar.dto.AtualizarStatusPedidoDTO;
import com.biolab.doce_laar.dto.ItemPedidoRequestDTO;
import com.biolab.doce_laar.dto.ItemPedidoResponseDTO;
import com.biolab.doce_laar.dto.PedidoRequestDTO;
import com.biolab.doce_laar.dto.PedidoResponseDTO;
import com.biolab.doce_laar.entity.Cliente;
import com.biolab.doce_laar.entity.Doce;
import com.biolab.doce_laar.entity.Funcionario;
import com.biolab.doce_laar.entity.ItemPedido;
import com.biolab.doce_laar.entity.Pedido;
import com.biolab.doce_laar.enums.StatusPedido;
import com.biolab.doce_laar.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;
    private final DoceService doceService;

    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Funcionario funcionario = funcionarioService.buscarEntidadePorId(dto.getFuncionarioId());

        Cliente cliente = null;
        if (dto.getClienteId() != null) {
            cliente = clienteService.buscarEntidadePorId(dto.getClienteId());
        }

        List<ItemPedido> itens = new ArrayList<>();
        if (dto.getItens() != null) {
            for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
                Doce doce = doceService.buscarEntidadePorId(itemDto.getDoceId());
                ItemPedido item = new ItemPedido(
                        null,
                        doce.getPrecoBase(),
                        itemDto.getQuantidade(),
                        doce
                );
                itens.add(item);
            }
        }

        Pedido pedido = new Pedido(
                null,
                LocalDateTime.now(),
                StatusPedido.PENDENTE,
                dto.getEhEncomenda() != null ? dto.getEhEncomenda() : false,
                dto.getDataEntrega(),
                cliente,
                funcionario,
                itens
        );

        return toResponseDTO(pedidoRepository.save(pedido));
    }

    public PedidoResponseDTO atualizarStatus(Long id, AtualizarStatusPedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido não encontrado com ID: " + id));

        pedido.setStatus(dto.getStatus());
        return toResponseDTO(pedidoRepository.save(pedido));
    }

    private PedidoResponseDTO toResponseDTO(Pedido pedido) {
        List<ItemPedidoResponseDTO> itensResponse = pedido.getItens() != null
                ? pedido.getItens().stream()
                .map(item -> new ItemPedidoResponseDTO(
                        item.getId(),
                        item.getDoce() != null ? item.getDoce().getId() : null,
                        item.getDoce() != null ? item.getDoce().getNome() : null,
                        item.getPrecoUnitario(),
                        item.getQuantidade()
                ))
                .toList()
                : List.of();

        String nomeCliente = pedido.getCliente() != null
                ? pedido.getCliente().getNome()
                : null;

        String nomeFuncionario = pedido.getFuncionario() != null
                ? pedido.getFuncionario().getNome()
                : null;

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                pedido.getStatus(),
                pedido.getEhEncomenda(),
                pedido.getDataEntrega(),
                nomeCliente,
                nomeFuncionario,
                itensResponse
        );
    }
}
