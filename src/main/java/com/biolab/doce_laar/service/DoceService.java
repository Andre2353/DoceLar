package com.biolab.doce_laar.service;

import com.biolab.doce_laar.dto.BoloRequestDTO;
import com.biolab.doce_laar.dto.DoceResponseDTO;
import com.biolab.doce_laar.dto.TortaRequestDTO;
import com.biolab.doce_laar.entity.Bolo;
import com.biolab.doce_laar.entity.Doce;
import com.biolab.doce_laar.entity.Torta;
import com.biolab.doce_laar.repository.DoceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoceService {

    private final DoceRepository doceRepository;

    public List<DoceResponseDTO> listarTodos() {
        return doceRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public DoceResponseDTO criarBolo(BoloRequestDTO dto) {
        Bolo bolo = new Bolo();
        bolo.setNome(dto.getNome());
        bolo.setPrecoBase(dto.getPrecoBase());
        bolo.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : true);
        bolo.setPesoKg(dto.getPesoKg());
        bolo.setRecheio(dto.getRecheio());
        return toResponseDTO(doceRepository.save(bolo));
    }

    public DoceResponseDTO criarTorta(TortaRequestDTO dto) {
        Torta torta = new Torta();
        torta.setNome(dto.getNome());
        torta.setPrecoBase(dto.getPrecoBase());
        torta.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : true);
        torta.setSaborMassa(dto.getSaborMassa());
        torta.setEhGelada(dto.getEhGelada());
        return toResponseDTO(doceRepository.save(torta));
    }

    public void deletar(Long id) {
        Doce doce = buscarEntidadePorId(id);
        doceRepository.delete(doce);
    }

    public Doce buscarEntidadePorId(Long id) {
        return doceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Doce não encontrado com ID: " + id));
    }

    private DoceResponseDTO toResponseDTO(Doce doce) {
        DoceResponseDTO dto = new DoceResponseDTO();
        dto.setId(doce.getId());
        dto.setNome(doce.getNome());
        dto.setPrecoBase(doce.getPrecoBase());
        dto.setAtivo(doce.getAtivo());

        if (doce instanceof Bolo bolo) {
            dto.setTipoDoce("BOLO");
            dto.setPesoKg(bolo.getPesoKg());
            dto.setRecheio(bolo.getRecheio());
        } else if (doce instanceof Torta torta) {
            dto.setTipoDoce("TORTA");
            dto.setSaborMassa(torta.getSaborMassa());
            dto.setEhGelada(torta.getEhGelada());
        }

        return dto;
    }
}
