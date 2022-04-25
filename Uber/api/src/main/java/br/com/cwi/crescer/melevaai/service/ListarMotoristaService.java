package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.BuscarMotoristaResponse;
import br.com.cwi.crescer.melevaai.mapper.BuscarMotoristaMapper;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarMotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private BuscarMotoristaMapper buscarMotoristaMapper;

    public List<BuscarMotoristaResponse> listar() {
        return motoristaRepository.findBySituacaoUsuario(SituacaoUsuario.OCUPADO)
                .stream()
                .map(motorista -> buscarMotoristaMapper.toResponse(motorista))
                .collect(Collectors.toList());
    }
}
