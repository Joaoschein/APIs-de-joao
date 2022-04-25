package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.DetalharFilmeResponse;
import br.com.cwi.crescer.oldflix.model.Filme;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DetalharFilmeMapper {
    public DetalharFilmeResponse toResponse (Filme entity) {
        return new ModelMapper().map(entity, DetalharFilmeResponse.class);
    }
}


