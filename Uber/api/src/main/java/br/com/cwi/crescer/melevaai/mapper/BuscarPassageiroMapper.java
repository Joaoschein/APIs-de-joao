package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.BuscarPassageiroResponse;
import br.com.cwi.crescer.melevaai.controller.response.DetalharCorridaResponse;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscarPassageiroMapper {

    @Autowired
    private DetalharCorridaAtualMapper detalharCorridaAtualMapper;

    public BuscarPassageiroResponse toResponse(Passageiro entity){
        BuscarPassageiroResponse response = new ModelMapper().map(entity, BuscarPassageiroResponse.class);
        DetalharCorridaResponse corridaAtualResponse = detalharCorridaAtualMapper.toResponse(entity);
        if(corridaAtualResponse != null){
            response.setCorrida(corridaAtualResponse);
        }
        return response;
    }
}
