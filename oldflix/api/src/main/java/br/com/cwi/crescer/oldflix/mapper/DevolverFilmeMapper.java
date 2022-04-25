package br.com.cwi.crescer.oldflix.mapper;


import br.com.cwi.crescer.oldflix.controller.response.DevolverFilmeResponse;
import br.com.cwi.crescer.oldflix.model.Filme;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DevolverFilmeMapper {
    public DevolverFilmeResponse toResponse (Filme entity) {

        return new ModelMapper().map(entity, DevolverFilmeResponse.class);
    }

}
