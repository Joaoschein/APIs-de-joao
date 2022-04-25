package br.com.cwi.crescer.melevaai.service.interno;

import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CorridaSituacaoService {
    public void verificarSolicitada(Corrida corrida){
        if(corrida.getSituacaoCorrida() != SituacaoCorrida.SOLICITADA){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta corrida não consta como solicitada");
        }
    }
}
