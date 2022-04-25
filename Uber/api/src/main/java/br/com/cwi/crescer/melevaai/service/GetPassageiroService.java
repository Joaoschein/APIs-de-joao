package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.BuscarPassageiroResponse;
import br.com.cwi.crescer.melevaai.mapper.BuscarPassageiroMapper;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPassageiroService {

    @Autowired
    private BuscarPassageiroService buscarPassageiroService;

    @Autowired
    private BuscarPassageiroMapper buscarPassageiroMapper;

    public BuscarPassageiroResponse buscar(Long id) {
        Passageiro passageiro = buscarPassageiroService.buscarPorId(id);

        return buscarPassageiroMapper.toResponse(passageiro);
    }
}
