package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.*;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.service.interno.VerificarFinalizarCorridaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import static br.com.cwi.crescer.melevaai.service.Util.CREDITOS_POR_SEGUNDO;

@Service
public class FinalizarCorridaService {

    @Autowired
    private CorridaRepository corridaRepository;

    @Autowired
    private BuscarCorridaService buscarCorridaService;

    @Autowired
    private VerificarFinalizarCorridaService verificarFinalizarCorridaService;

    public void finalizar(Long id) {
        Corrida corrida = buscarCorridaService.buscarPorId(id);

        verificarFinalizarCorridaService.verificarFinalizarCorrida(corrida);

        long tempoCorrida =  ChronoUnit.SECONDS.between(corrida.getHoraInicio(), corrida.getHoraFinal());
        BigDecimal precoCorrida = BigDecimal.valueOf(tempoCorrida * CREDITOS_POR_SEGUNDO);

        Passageiro passageiro = corrida.getPassageiro();
        Motorista motorista = corrida.getMotorista();

        if(passageiro.getCreditos().compareTo(precoCorrida) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O passageiro não possui créditos suficientes em sua carteira");
        }

        passageiro.setCreditos(passageiro.getCreditos().subtract(precoCorrida));
        passageiro.setSituacaoUsuario(SituacaoUsuario.LIVRE);
        motorista.setCreditos(motorista.getCreditos().add(precoCorrida));
        motorista.setSituacaoUsuario(SituacaoUsuario.LIVRE);

        corrida.setSituacaoCorrida(SituacaoCorrida.FINALIZADA);
        corrida.setMotorista(motorista);
        corrida.setPassageiro(passageiro);

        corridaRepository.save(corrida);
    }
}
