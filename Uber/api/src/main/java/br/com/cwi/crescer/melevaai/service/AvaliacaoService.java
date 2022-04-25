package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.AvaliacaoRequest;
import br.com.cwi.crescer.melevaai.model.*;
import br.com.cwi.crescer.melevaai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AvaliacaoService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private BuscarMotoristaService buscarMotoristaService;

    @Autowired
    private BuscarPassageiroService buscarPassageiroService;

    @Autowired
    private BuscarCorridaService buscarCorridaService;

    @Autowired
    private CorridaRepository corridaRepository;

    public void avaliarMotorista(Long id, AvaliacaoRequest request) {
        Corrida corrida = buscarCorridaService.buscarPorId(id);

        corrida.setAvaliacaoMotorista(request.getNota());
        corridaRepository.save(corrida);

        Motorista motorista = corrida.getMotorista();
        BigDecimal mediaAvaliacoes = corridaRepository.getMediaDeAvaliacoesMotorista(motorista);
        motorista.setAvaliacao(mediaAvaliacoes);

        motoristaRepository.save(motorista);
    }

    public void avaliarPassageiro(Long id, AvaliacaoRequest request) {
        Corrida corrida = buscarCorridaService.buscarPorId(id);

        corrida.setAvaliacaoPassageiro(request.getNota());
        corridaRepository.save(corrida);

        Passageiro passageiro = corrida.getPassageiro();
        BigDecimal mediaAvaliacoes = corridaRepository.getMediaDeAvaliacoesPassageiro(passageiro);
        passageiro.setAvaliacao(mediaAvaliacoes);

        passageiroRepository.save(passageiro);
    }
}
