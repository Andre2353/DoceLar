package com.biolab.doce_laar.service;

import com.biolab.doce_laar.dto.FuncionarioRequestDTO;
import com.biolab.doce_laar.dto.FuncionarioResponseDTO;
import com.biolab.doce_laar.entity.Funcionario;
import com.biolab.doce_laar.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = buscarEntidadePorId(id);
        return toResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO criar(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario(null, dto.getNome(), dto.getCargo());
        return toResponseDTO(funcionarioRepository.save(funcionario));
    }

    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = buscarEntidadePorId(id);
        funcionario.setNome(dto.getNome());
        funcionario.setCargo(dto.getCargo());
        return toResponseDTO(funcionarioRepository.save(funcionario));
    }

    public void deletar(Long id) {
        Funcionario funcionario = buscarEntidadePorId(id);
        funcionarioRepository.delete(funcionario);
    }

    public Funcionario buscarEntidadePorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Funcionário não encontrado com ID: " + id));
    }

    private FuncionarioResponseDTO toResponseDTO(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo()
        );
    }
}
