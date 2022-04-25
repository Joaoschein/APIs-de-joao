package br.com.cwi.crescer.melevaai.service.interno;

import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MotoristaSituacaoService {
    public void verificarLivre(Motorista motorista){
        if(motorista.getSituacaoUsuario() != SituacaoUsuario.LIVRE){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O motorista não está livre para realizar uma corrida");
        }
    }

    public void verificarAtivo(Motorista motorista){
        if(motorista.getSituacaoUsuario() == SituacaoUsuario.INATIVO){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este motorista está inativo");
        }
    }
}
