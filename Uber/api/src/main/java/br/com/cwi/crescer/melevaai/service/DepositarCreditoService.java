package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.CreditoRequest;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import br.com.cwi.crescer.melevaai.service.interno.PassageiroSituacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositarCreditoService {

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private BuscarPassageiroService buscarPassageiroService;

    @Autowired
    private PassageiroSituacaoService passageiroSituacaoService;

    public void depositar(Long id, CreditoRequest request) {
        Passageiro passageiro = buscarPassageiroService.buscarPorId(id);
        passageiroSituacaoService.verificarAtivo(passageiro);

        passageiro.setCreditos(passageiro.getCreditos().add(request.getValor()));

        passageiroRepository.save(passageiro);
    }
}
