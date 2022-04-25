package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.DetalharCorridaResponse;
import br.com.cwi.crescer.melevaai.controller.response.BuscarMotoristaResponse;
import br.com.cwi.crescer.melevaai.model.Motorista;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BuscarMotoristaMapper {

    @Autowired
    private DetalharCorridaAtualMapper detalharCorridaAtualMapper;

    public BuscarMotoristaResponse toResponse(Motorista entity){
        BuscarMotoristaResponse response = new ModelMapper().map(entity, BuscarMotoristaResponse.class);
        DetalharCorridaResponse corridaAtualResponse = detalharCorridaAtualMapper.toResponse(entity);
        if(corridaAtualResponse != null){
            response.setCorrida(corridaAtualResponse);
        }
        return response;
    }
}
