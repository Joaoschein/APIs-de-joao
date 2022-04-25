package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import br.com.cwi.crescer.melevaai.service.interno.MotoristaSituacaoService;
import br.com.cwi.crescer.melevaai.service.interno.PassageiroSituacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesativarService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private BuscarMotoristaService buscarMotoristaService;

    @Autowired
    private BuscarPassageiroService buscarPassageiroService;

    @Autowired
    private MotoristaSituacaoService motoristaSituacaoService;

    @Autowired
    private PassageiroSituacaoService passageiroSituacaoService;

    public void desativarMotorista(Long id) {
        Motorista motorista = buscarMotoristaService.buscarPorId(id);

        motoristaSituacaoService.verificarLivre(motorista);

        motorista.setSituacaoUsuario(SituacaoUsuario.INATIVO);

        motoristaRepository.save(motorista);
    }

    public void desativarPassageiro(Long id){
        Passageiro passageiro = buscarPassageiroService.buscarPorId(id);

        passageiroSituacaoService.verificarLivre(passageiro);

        passageiro.setSituacaoUsuario(SituacaoUsuario.INATIVO);

        passageiroRepository.save(passageiro);
    }
}
