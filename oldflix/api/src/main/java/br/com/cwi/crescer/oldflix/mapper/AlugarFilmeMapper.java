package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.AlugarFilmeResponse;
import br.com.cwi.crescer.oldflix.model.Filme;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlugarFilmeMapper {

    public AlugarFilmeResponse toResponse (Filme entity) {

        return new ModelMapper().map(entity, AlugarFilmeResponse.class);
    }
}
