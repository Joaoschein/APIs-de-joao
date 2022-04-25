package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.BuscarPassageiroResponse;
import br.com.cwi.crescer.melevaai.mapper.BuscarPassageiroMapper;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
import br.com.cwi.crescer.melevaai.util.PassageiroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarPassageiroServiceTest {

    @InjectMocks
    private ListarPassageiroService service;

    @Mock
    private PassageiroRepository passageiroRepository;

    @Mock
    private BuscarPassageiroMapper buscarPassageiroMapper;

    @Test
    @DisplayName("Deve retornar lista de passageiros com sucesso")
    void deveRetornarListaDePassageiros() {
        List<Passageiro> listaPassageiro = Arrays.asList(PassageiroFactory.get());

        BuscarPassageiroResponse buscarResponse = new BuscarPassageiroResponse();

        when(passageiroRepository.findBySituacaoUsuario(SituacaoUsuario.LIVRE))
                .thenReturn(listaPassageiro);
        when(buscarPassageiroMapper.toResponse(PassageiroFactory.get()))
                .thenReturn(buscarResponse);

        List<BuscarPassageiroResponse> resultado = service.listar();

        verify(passageiroRepository).findBySituacaoUsuario(SituacaoUsuario.LIVRE);
        verify(buscarPassageiroMapper).toResponse(PassageiroFactory.get());

        assertEquals(1, resultado.size());
    }
}