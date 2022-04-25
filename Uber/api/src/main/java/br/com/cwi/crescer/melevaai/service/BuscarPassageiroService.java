package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuscarPassageiroService {

    @Autowired
    private PassageiroRepository passageiroRepository;

    public Passageiro buscarPorId(Long id){
        return passageiroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passageiro não encontrado"));
    }
}
