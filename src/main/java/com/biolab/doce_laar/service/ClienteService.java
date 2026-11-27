package com.biolab.doce_laar.service;

import com.biolab.doce_laar.dto.ClienteRequestDTO;
import com.biolab.doce_laar.dto.ClienteResponseDTO;
import com.biolab.doce_laar.entity.Cliente;
import com.biolab.doce_laar.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getTelefone());
        return toResponseDTO(clienteRepository.save(cliente));
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = buscarEntidadePorId(id);
        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        return toResponseDTO(clienteRepository.save(cliente));
    }

    public void deletar(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        clienteRepository.delete(cliente);
    }

    public Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado com ID: " + id));
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone()
        );
    }
}
