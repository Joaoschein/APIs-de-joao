package br.com.cwi.crescer.melevaai.service.interno;

import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PassageiroSituacaoService {
    public void verificarLivre(Passageiro passageiro){
        if(passageiro.getSituacaoUsuario() != SituacaoUsuario.LIVRE){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O passageiro não está livre para realizar uma corrida");
        }
    }

    public void verificarAtivo(Passageiro passageiro){
        if(passageiro.getSituacaoUsuario() == SituacaoUsuario.INATIVO){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este passageiro está inativo");
        }
    }
}
