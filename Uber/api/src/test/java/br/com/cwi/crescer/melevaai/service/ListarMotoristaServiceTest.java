package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.BuscarMotoristaResponse;
import br.com.cwi.crescer.melevaai.mapper.BuscarMotoristaMapper;
import br.com.cwi.crescer.melevaai.mapper.BuscarPassageiroMapper;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
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
class ListarMotoristaServiceTest {

    @InjectMocks
    private ListarMotoristaService service;

    @Mock
    private MotoristaRepository motoristaRepository;

    @Mock
    private BuscarMotoristaMapper buscarMotoristaMapper;

    @Test
    @DisplayName("Deve retornar lista de motoristas ocupados com corrida com sucesso")
    void deveRetornarResponseListaDeMotoristasOcupados() {
        List<Motorista> listaMotoristas = Arrays.asList(MotoristaFactory.getMotoristaComCorrida());

        BuscarMotoristaResponse buscarResponse = new BuscarMotoristaResponse();

        when(motoristaRepository.findBySituacaoUsuario(SituacaoUsuario.OCUPADO))
                .thenReturn(listaMotoristas);
        when(buscarMotoristaMapper.toResponse(MotoristaFactory.getMotoristaComCorrida()))
                .thenReturn(buscarResponse);

        List<BuscarMotoristaResponse> resultado = service.listar();

        verify(motoristaRepository).findBySituacaoUsuario(SituacaoUsuario.OCUPADO);
        verify(buscarMotoristaMapper).toResponse(MotoristaFactory.getMotoristaComCorrida());

        assertEquals(1, resultado.size());
    }
}