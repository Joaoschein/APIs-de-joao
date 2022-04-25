package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.request.IncluirFilmeRequest;
import br.com.cwi.crescer.oldflix.controller.response.IncluirFilmeResponse;
import br.com.cwi.crescer.oldflix.model.Filme;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class IncluirFilmeMapper {

    public Filme toEntity(IncluirFilmeRequest request) {
        Filme filme = new ModelMapper().map(request, Filme.class);
        filme.setDisponivel(true);
        filme.setResponsavel("");
        return filme;
    }

    public IncluirFilmeResponse toResponse(Filme entity) {
        return new ModelMapper().map(entity, IncluirFilmeResponse.class);
    }
}
