package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import br.com.cwi.crescer.melevaai.controller.request.SolicitarCorridaRequest;
import br.com.cwi.crescer.melevaai.controller.response.SolicitarCorridaResponse;
import br.com.cwi.crescer.melevaai.mapper.VeiculoSolicitarMapper;
import br.com.cwi.crescer.melevaai.model.*;
import br.com.cwi.crescer.melevaai.service.interno.MotoristaSituacaoService;
import br.com.cwi.crescer.melevaai.service.interno.PassageiroSituacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.crescer.melevaai.mapper.VeiculoSolicitarMapper.toVeiculoSolicitarResponse;

@Service
public class SolicitarCorridaService {

    @Autowired
    private CorridaRepository corridaRepository;

    @Autowired
    private VeiculoSolicitarMapper veiculoSolicitarMapper;

    @Autowired
    private BuscarPassageiroService buscarPassageiroService;

    @Autowired
    private BuscarMotoristaService buscarMotoristaService;

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private GerarTempoEstimadoService gerarTempoEstimadoService;

    @Autowired
    private MotoristaSituacaoService motoristaSituacaoService;

    @Autowired
    private PassageiroSituacaoService passageiroSituacaoService;

    public SolicitarCorridaResponse solicitar(SolicitarCorridaRequest request) {
        Passageiro passageiro = buscarPassageiroService.buscarPorId(request.getId());
        passageiroSituacaoService.verificarLivre(passageiro);

        Motorista motorista = buscarMotoristaService.buscarLivreComMaiorAvaliacao();
        motoristaSituacaoService.verificarLivre(motorista);

        Double distancia = calcularDistacia(
                request.getPontoFinalX(),
                request.getPontoInicialX(),
                request.getPontoFinalY(),
                request.getPontoInicialY()
        );

        passageiro.setSituacaoUsuario(SituacaoUsuario.OCUPADO);
        motorista.setSituacaoUsuario(SituacaoUsuario.OCUPADO);

        Corrida corrida = Corrida.builder()
                .distancia(distancia)
                .situacaoCorrida(SituacaoCorrida.SOLICITADA)
                .motorista(motorista)
                .passageiro(passageiro)
                .build();

        corrida = corridaRepository.save(corrida);

        return SolicitarCorridaResponse.builder()
                .id(corrida.getId())
                .nomeMotorista(motorista.getNome())
                .veiculo(toVeiculoSolicitarResponse(motorista.getVeiculo()))
                .tempoEstimadoChegada(gerarTempoEstimadoService.gerar())
                .build();
    }

    private Double calcularDistacia(Double x1, Double x2, Double y1, Double y2){
        Double elemento1 = Math.pow((x2 - x1), 2);
        Double elemento2 = Math.pow((y2 - y1), 2);

        return Math.sqrt(elemento1 + elemento2);
    }
}
