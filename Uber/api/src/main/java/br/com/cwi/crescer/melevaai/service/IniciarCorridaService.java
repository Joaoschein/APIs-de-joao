package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.IniciarCorridaResponse;
import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.service.interno.CorridaSituacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.cwi.crescer.melevaai.service.Util.VELOCIDADE_EM_KM_POR_HORA;


@Service
public class IniciarCorridaService {

    @Autowired
    private CorridaRepository corridaRepository;

    @Autowired
    private TimeService timeService;

    @Autowired
    private BuscarCorridaService buscarCorridaService;

    @Autowired
    private CorridaSituacaoService corridaSituacaoService;

    private static final int SEGUNDOS_EM_UMA_HORA = 3600;

    public IniciarCorridaResponse iniciar(Long id) {

        Corrida corrida = buscarCorridaService.buscarPorId(id);

        corridaSituacaoService.verificarSolicitada(corrida);

        Long tempoEstimado = Math.round((corrida.getDistancia() / VELOCIDADE_EM_KM_POR_HORA ) * SEGUNDOS_EM_UMA_HORA);

        corrida.setHoraInicio(timeService.nowWithTime());
        corrida.setHoraFinal(timeService.nowWithTime().plusSeconds(tempoEstimado));
        corrida.setSituacaoCorrida(SituacaoCorrida.INICIADA);
        corrida.getMotorista().setSituacaoUsuario(SituacaoUsuario.OCUPADO);
        corrida.getPassageiro().setSituacaoUsuario(SituacaoUsuario.OCUPADO);

        corridaRepository.save(corrida);

        return IniciarCorridaResponse.builder()
                .tempoEstimado(tempoEstimado)
                .valorEstimado(BigDecimal.valueOf(tempoEstimado * 0.20).setScale(2, RoundingMode.HALF_UP))
                .build();
    }
}
