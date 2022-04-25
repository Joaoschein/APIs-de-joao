package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class BuscarMotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    public Motorista buscarPorId(Long id){
        return motoristaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));
    }

    public Motorista buscarLivreComMaiorAvaliacao(){
        Motorista motorista = motoristaRepository.findFirstBySituacaoUsuarioOrderByAvaliacaoDesc(SituacaoUsuario.LIVRE);
        if(motorista == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existem motoristas livres");
        }else{
            return motorista;
        }
    }
}
