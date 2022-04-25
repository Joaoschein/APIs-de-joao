package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.BuscarMotoristaResponse;
import br.com.cwi.crescer.melevaai.mapper.BuscarMotoristaMapper;
import br.com.cwi.crescer.melevaai.model.Motorista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetMotoristaService {

    @Autowired
    private BuscarMotoristaService buscarMotoristaService;

    @Autowired
    private BuscarMotoristaMapper buscarMotoristaMapper;

    public BuscarMotoristaResponse buscar(Long id) {
        Motorista motorista = buscarMotoristaService.buscarPorId(id);

        return buscarMotoristaMapper.toResponse(motorista);
    }
}
