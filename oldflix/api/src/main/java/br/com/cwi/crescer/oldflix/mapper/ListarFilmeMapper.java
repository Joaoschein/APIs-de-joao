package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.ListarFilmeResponse;
import br.com.cwi.crescer.oldflix.model.Filme;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ListarFilmeMapper {
    public ListarFilmeResponse toResponse(Filme entity) {
        return new ModelMapper().map(entity, ListarFilmeResponse.class);
    }
}
