package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.CreditoRequest;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.service.interno.MotoristaSituacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SacarCreditoService {

    @Autowired
    private BuscarMotoristaService buscarMotoristaService;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private MotoristaSituacaoService motoristaSituacaoService;

    public void sacar(Long id, CreditoRequest request) {
        Motorista motorista = buscarMotoristaService.buscarPorId(id);
        motoristaSituacaoService.verificarAtivo(motorista);

        if(motorista.getCreditos().compareTo(request.getValor()) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente");
        }

        motorista.setCreditos(motorista.getCreditos().subtract(request.getValor()));

        motoristaRepository.save(motorista);
    }
}
