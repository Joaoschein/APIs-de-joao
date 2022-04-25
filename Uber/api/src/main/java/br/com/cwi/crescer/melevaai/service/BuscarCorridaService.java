package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuscarCorridaService {

    @Autowired
    private CorridaRepository corridaRepository;

    public Corrida buscarPorId(Long id){
        return corridaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Corrida não encontrada"));
    }
}
