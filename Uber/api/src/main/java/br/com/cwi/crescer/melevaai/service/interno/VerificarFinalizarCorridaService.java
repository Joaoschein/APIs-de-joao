package br.com.cwi.crescer.melevaai.service.interno;

import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import br.com.cwi.crescer.melevaai.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VerificarFinalizarCorridaService {

    @Autowired
    private TimeService timeService;

    public void verificarFinalizarCorrida(Corrida corrida){
        if(corrida.getSituacaoCorrida() != SituacaoCorrida.INICIADA){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível finalizar uma corrida que não está iniciada");
        }

        if(timeService.nowWithTime().isBefore(corrida.getHoraFinal())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O motorista ainda não chegou no destino");
        }
    }
}
