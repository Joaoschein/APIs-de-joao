package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.BuscarPassageiroResponse;
import br.com.cwi.crescer.melevaai.mapper.BuscarPassageiroMapper;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPassageiroService {

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private BuscarPassageiroMapper buscarPassageiroMapper;

    public List<BuscarPassageiroResponse> listar() {
        return passageiroRepository.findBySituacaoUsuario(SituacaoUsuario.LIVRE)
                .stream()
                .map(passageiro -> buscarPassageiroMapper.toResponse(passageiro))
                .collect(Collectors.toList());
    }
}
